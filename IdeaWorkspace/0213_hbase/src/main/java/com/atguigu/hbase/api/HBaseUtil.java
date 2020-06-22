package com.atguigu.hbase.api;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

import javax.sql.RowSet;
import java.io.IOException;

public class HBaseUtil  {

    private  static Connection connection=null;

    /**
     * 获取连接
     */
    static{

        try {
            Configuration configuration = HBaseConfiguration.create();
            //zookeeper集群地址,不用谢端口号
            configuration.set("hbase.zookeeper.quorum","hadoop102,hadoop103,hadoop104");
            //设置zookeeper 的客户端端口号
            configuration.set("hbase.zookeeper.property.clientPort","2181");
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建表
     * @param tableName
     * @param families
     * @throws IOException
     */
    public static void createTable(String tableName,String... families) throws IOException {

        Admin admin = connection.getAdmin();
        //表的描述器
        HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        for (String family : families) {
            //列族的描述器
            HColumnDescriptor familyDescriptor = new HColumnDescriptor(family);
            tableDescriptor.addFamily(familyDescriptor);
        }
        admin.createTable(tableDescriptor);

        admin.close();
    }

    /**
     * 修改表
     * @param tableName
     * @param family
     * @throws IOException
     */
    public static void modifyTable(String tableName,String family) throws IOException {
        Admin admin = connection.getAdmin();
        //获取列族描述器
        HColumnDescriptor familyDescriptor = new HColumnDescriptor(family);
        //修改版本
        familyDescriptor.setMaxVersions(3);
        //修改表信息
        admin.modifyColumn(TableName.valueOf(tableName),familyDescriptor);
        admin.close();
    }

    /**
     * 删除表
     * @param tableName
     * @throws Exception
     */
    public static void deleteTable(String tableName) throws  Exception{
        Admin admin = connection.getAdmin();
        if (admin.tableExists(TableName.valueOf(tableName))){
            admin.disableTable(TableName.valueOf(tableName));
            admin.deleteTable(TableName.valueOf(tableName));
        }else {
            System.err.println("表不存在！");
        }
        admin.close();
    }

    /**
     * 插入数据
     * @param tableName
     * @param rowKey
     * @param family
     * @param value
     * @throws Exception
     */
    public  static void putCell(String tableName,String rowKey,String family,String colume,String value)throws  Exception{
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(family),Bytes.toBytes(colume),Bytes.toBytes(value));
        table.put(put);

        table.close();
    }

    /**
     * 查询数据(单列行)
     * @param tablename
     * @param rowKey
     * @throws Exception
     */
    public  static  void  getRow(String tablename,String rowKey) throws Exception{
        Table table = connection.getTable(TableName.valueOf(tablename));
        Get get = new Get(Bytes.toBytes(rowKey));
        Result result = table.get(get);
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            //获取值
            byte[] value = CellUtil.cloneValue(cell);
            //获取列族
            byte[] family = CellUtil.cloneFamily(cell);
            //获取列名
            byte[] qualifier = CellUtil.cloneQualifier(cell);

            System.out.println(Bytes.toString(value));
            System.out.println(Bytes.toString(family));
            System.out.println(Bytes.toString(qualifier));

            table.close();
        }
    }

    /**
     * 查询多行
     * @param tableName
     * @param startRow
     * @param stopRow
     */
    public  static void  getRowScan(String tableName,String startRow,String stopRow)
            throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan(Bytes.toBytes(startRow), Bytes.toBytes(stopRow));
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                byte[] row = CellUtil.cloneRow(cell);
                byte[] family = CellUtil.cloneFamily(cell);
                byte[] qualifier = CellUtil.cloneQualifier(cell);
                byte[] value = CellUtil.cloneValue(cell);
                System.out.println(
                        "rowKey:"+Bytes.toString(row)+"->"
                        +"family:"+Bytes.toString(family)+"->"
                        +"qualifier:"+Bytes.toString(qualifier)+"->"
                        +"value:"+Bytes.toString(value));
            }
        }
        resultScanner.close();
        table.close();
    }

    /**
     * 使用scan结合filter查询
     * @param tableName
     * @param family
     * @param column
     * @param value
     * @throws IOException
     */
    public  static void getRowsByColumnFilter(String tableName,String family
            ,String column,String value) throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));

        Scan scan = new Scan();
        SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes(family),
                Bytes.toBytes(column), CompareFilter.CompareOp.EQUAL, Bytes.toBytes(value));
        //  如果没有指定列，则过滤掉
        filter.setFilterIfMissing(true);
        scan.setFilter(filter);

        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                byte[] row = CellUtil.cloneRow(cell);
                byte[] family1 = CellUtil.cloneFamily(cell);
                byte[] qualifier = CellUtil.cloneQualifier(cell);
                byte[] value1 = CellUtil.cloneValue(cell);
                System.out.println(
                        "rowKey:"+Bytes.toString(row)+"->"
                                +"family:"+Bytes.toString(family1)+"->"
                                +"qualifier:"+Bytes.toString(qualifier)+"->"
                                +"value:"+Bytes.toString(value1));
            }
        }
        scanner.close();
        table.close();
    }

    /**
     * 使用scan结合filterList查询
     * @param tableName
     * @param family
     * @param column
     * @param value
     * @throws IOException
     */
    public  static void getRowsByColumnsFilter(String tableName,String family
            ,String[] column,String value) throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();

        SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes(family),
                Bytes.toBytes(column[0]), CompareFilter.CompareOp.EQUAL, Bytes.toBytes(value));

        SingleColumnValueFilter filter1 = new SingleColumnValueFilter(Bytes.toBytes(family),
                Bytes.toBytes(column[1]), CompareFilter.CompareOp.EQUAL, Bytes.toBytes(value));

        //  如果没有指定列，则过滤掉
        filter.setFilterIfMissing(true);
        //关联两个filter的关系与（MUST_PASS_ALL）和或(MUST_PASS_ONE)的关系
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        filterList.addFilter(filter);
        filterList.addFilter(filter1);
        scan.setFilter(filterList);

        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                byte[] row = CellUtil.cloneRow(cell);
                byte[] family1 = CellUtil.cloneFamily(cell);
                byte[] qualifier = CellUtil.cloneQualifier(cell);
                byte[] value1 = CellUtil.cloneValue(cell);
                System.out.println(
                        "rowKey:"+Bytes.toString(row)+"->"
                                +"family:"+Bytes.toString(family1)+"->"
                                +"qualifier:"+Bytes.toString(qualifier)+"->"
                                +"value:"+Bytes.toString(value1));
            }
        }
        scanner.close();
        table.close();
    }

    /**
     * 删除一行数据
     * @param tableName
     * @param rowKey
     * @throws IOException
     */
    public  static  void deleteRow(String tableName,String rowKey) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);
        table.close();

    }
    /**
     * 删除一列数据
     * @param tableName
     * @param rowKey
     * @throws IOException
     */
    public  static  void deleteCloumn(String tableName,String rowKey,String family,String column) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        //删除最新版本
        delete.addColumn(Bytes.toBytes(family),Bytes.toBytes(column));
        //删除所有版本
//        delete.addColumns(Bytes.toBytes(family),Bytes.toBytes(column));
        table.delete(delete);
        table.close();

    }
    /**
     * 测试
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {

//        createTable("class","info");
//        createTable("class1","info");
//        modifyTable("class","info");
//        deleteTable("class1");
//        putCell("class","1003","info","sex","nan");
//        getRow("class","1001");
        getRowScan("class","1001","1003");
//        getRowsByColumnFilter("class","info","name","zhangliu");
//        deleteRow("class","1001")；
//        deleteCloumn("class","1003","info","name");
    }
}

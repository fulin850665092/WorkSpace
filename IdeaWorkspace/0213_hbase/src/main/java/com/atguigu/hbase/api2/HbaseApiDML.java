package com.atguigu.hbase.api2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.protobuf.generated.FilterProtos;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HbaseApiDML {

    private static Connection connection= null;

    static {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop102,hadoop103,hadoop104");
        try {
            connection= ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个cell
     * @param tableName
     * @param rowKey
     * @param family
     * @param column
     * @param value
     * @throws IOException
     */
    public static void putCell(String tableName,String rowKey,String family,String column,String value) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        //一个put对象绑定一行数据
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes(value));
        table.put(put);
        table.close();
    }
    /**
     * 增加一个cell(实验)
     * @param tableName
     * @param rowKey
     * @param familys
     * @param column
     * @param value
     * @throws IOException
     */
    public static void putCell2(String tableName, String rowKey, List<String> familys, String column, String value) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        //一个put对象绑定一行数据
        Put put = new Put(Bytes.toBytes(rowKey));
        for (String family : familys) {
            put.addColumn(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes(value));
            table.put(put);
        }
        table.close();
    }

    /**
     * 查询一行数据
     * @param tableName
     * @param rowKey
     * @throws IOException
     */
    public  static  void getRow(String tableName,String rowKey) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        //一行有多个列，返回多个列
        Result result = table.get(get);
        //获取多个列
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
        }
        table.close();
    }

    /**
     * 查询多行数据(scan 'stu1' ,{STARTROW=>'1001',STOPROW=>'1003'})
     * @param tableName
     * @param startRowKey
     * @param stopRowKey
     * @throws IOException
     */
    public  static  void getRowsByRowKeyRange(String tableName,String startRowKey,String stopRowKey) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(startRowKey));
        scan.setStopRow(Bytes.toBytes(stopRowKey));
        //返回多行数据(类似result的集合)
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                System.out.println("family:"+Bytes.toString(CellUtil.cloneFamily(cell))+"==>"+
                                    "colunm:"+Bytes.toString(CellUtil.cloneQualifier(cell))+"==>"+
                                    "value"+Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
        resultScanner.close();
        table.close();
    }

    /**
     * 查询多行数据（根据过滤器(rowKey前缀)）
     * @param tableName
     * @param prefix
     * @throws IOException
     */
    public  static  void getRowsByRokeyFilter(String tableName,String prefix) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        scan.setRowPrefixFilter(Bytes.toBytes(prefix));
        //返回多行数据(类似result的集合)
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                System.out.println("family:"+Bytes.toString(CellUtil.cloneFamily(cell))+"==>"+
                        "colunm:"+Bytes.toString(CellUtil.cloneQualifier(cell))+"==>"+
                        "value:"+Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
        resultScanner.close();
        table.close();
    }
    /**
     * 查询多行数据（根据过滤器(value的值，效率慢)）
     * @param tableName
     * @param column
     * @throws IOException
     */
    public  static  void getRowsByColumnFilter(String tableName,String family,String column,String value) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();

        SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(column), CompareFilter.CompareOp.EQUAL, Bytes.toBytes(value));
        singleColumnValueFilter.setFilterIfMissing(true);
        scan.setFilter(singleColumnValueFilter);

        //返回多行数据(类似result的集合)
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                System.out.println("family:"+Bytes.toString(CellUtil.cloneFamily(cell))+"==>"+
                        "colunm:"+Bytes.toString(CellUtil.cloneQualifier(cell))+"==>"+
                        "value:"+Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
        resultScanner.close();
        table.close();
    }
    /**
     * 获取莫一列
     * @param tableName
     * @param rowKey
     * @param family
     * @param column
     */
    public  static void getColunm(String tableName,String rowKey,String family,String column ) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addColumn(Bytes.toBytes(family),Bytes.toBytes(column));
        Result result = table.get(get);
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
        }
        table.close();
    }
    /**
     * 获取莫一列所有版本
     * @param tableName
     * @param rowKey
     * @param family
     * @param column
     */
    public  static void getColunmVersions(String tableName,String rowKey,String family,String column ) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        //不设置，就是返回最大默认版本
        get.setMaxVersions();
        get.addColumn(Bytes.toBytes(family),Bytes.toBytes(column));
        Result result = table.get(get);
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
        }
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
     * 删除一列数据的最新版本
     * @param tableName
     * @param rowKey
     * @throws IOException
     */
    public  static  void deleteColunm(String tableName,String rowKey,String family,String colunm) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));

        Delete delete = new Delete(Bytes.toBytes(rowKey));
        //删除最新版本
        delete.addColumn(Bytes.toBytes(family),Bytes.toBytes(colunm));
        table.delete(delete);
        table.close();
    }
    /**
     * 删除一列的所有版本数据,
     * @param tableName
     * @param rowKey
     * @throws IOException
     */
    public  static  void deleteColunmAllVersions(String tableName,String rowKey,String family,String colunm) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));

        Delete delete = new Delete(Bytes.toBytes(rowKey));
        //删除所有版本
        delete.addColumns(Bytes.toBytes(family),Bytes.toBytes(colunm));
        table.delete(delete);
        table.close();
    }

    public static void main(String[] args) throws IOException {
//        putCell("stu1","1001","info","name","zs");
//        List<String> list= new ArrayList<String>();
//        list.add("info");
//        list.add("age");
//        putCell2("stu1","1003",list,"name","zs");
//        getRow("stu1","1001");
//        getColunm("stu1","1001","info","name");
//        getColunmVersions("stu1","1001","info","name");
//        getRowsByRowKeyRange("stu1","1001","1003");
//        getRowsByColumnFilter("stu1","info","name","zs");
//        deleteRow("stu1","1001");
        deleteColunm("stu1","1002","info","name");
//        deleteColunmAllVersions("stu1","1003","info","name");
     }

}

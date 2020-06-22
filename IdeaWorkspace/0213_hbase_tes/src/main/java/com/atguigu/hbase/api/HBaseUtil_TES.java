package com.atguigu.hbase.api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import sun.tools.jconsole.Tab;

import java.io.IOException;

public class HBaseUtil_TES {
    /**
     * 单例
     */
    private static Connection connection= null;
    static {
        Configuration conf = HBaseConfiguration.create();
        //zookeeper集群地址,不用谢端口号
        conf.set("hbase.zookeeper.quorum","hadoop102,hadoop103,hadoop104");
        //设置zookeeper 的客户端端口号
        conf.set("hbase.zookeeper.property.clientPort","2181");
        try {
            connection = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建表
     * @param tableName
     * @param famiyNames
     * @throws IOException
     */
     public static void createTable(String tableName,String...famiyNames) throws IOException {

         Admin admin = connection.getAdmin();
         if (admin.tableExists(TableName.valueOf(tableName))){
             return;
         }
         HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
         for (String famiyName : famiyNames) {
             HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(famiyName);
             hTableDescriptor.addFamily(hColumnDescriptor);
         }
         admin.createTable(hTableDescriptor);
         admin.close();
     }

    /**
     * 修改列族，版本数
     * @param tableName
     * @param family
     * @throws IOException
     */
     public static void modifyTable(String tableName,String family,int maxVersions ) throws IOException {
         Admin admin = connection.getAdmin();

         HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(family);
         hColumnDescriptor.setMaxVersions(maxVersions);

         admin.modifyColumn(TableName.valueOf(tableName),hColumnDescriptor);

         admin.close();

     }

    /**
     * 删除表
     * @param tableName
     * @throws IOException
     */
    public static void dropTable(String tableName) throws IOException {
        Admin admin = connection.getAdmin();
        if (! admin.tableExists(TableName.valueOf(tableName))){
            System.err.println("table："+tableName+"不存在！");
            return;
        }
        //先disable 表
        admin.disableTable(TableName.valueOf(tableName));
        admin.deleteTable(TableName.valueOf(tableName));
        admin.close();
    }

    /**
     * 增加一列(cell)
     * @param tableName
     * @throws IOException
     */
    public static void putCell(String tableName,String rowkey,String family,String column,String value) throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));
        //传一个rowkey
        Put put = new Put(Bytes.toBytes(rowkey));
        put.addColumn(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes(value));
        table.put(put);
        table.close();
    }
    /**
     * 获取行的值（row）
     * @param tableName
     * @throws IOException
     */
    public static void getRow(String tableName,String rowkey) throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));
        //传一个rowkey
        Get get = new Get(Bytes.toBytes(rowkey));
        Result result = table.get(get);
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            byte[] cloneFamily = CellUtil.cloneFamily(cell);
            byte[] qualifier = CellUtil.cloneQualifier(cell);
            byte[] value = CellUtil.cloneValue(cell);
            System.out.println(
                    Bytes.toString(cloneFamily)+","+
                    Bytes.toString(qualifier)+","+
                    Bytes.toString(value)
                    );
        }
        table.close();
    }
    /**
     * 获取cell的值（cell）
     * @param tableName
     * @throws IOException
     */
    public static void getCell(String tableName,String rowkey,String family,String column) throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));
        //传一个rowkey
        Get get = new Get(Bytes.toBytes(rowkey));
        get.addColumn(Bytes.toBytes(family),Bytes.toBytes(column));
        Result result = table.get(get);
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            byte[] cloneFamily = CellUtil.cloneFamily(cell);
            byte[] qualifier = CellUtil.cloneQualifier(cell);
            byte[] value = CellUtil.cloneValue(cell);
            System.out.println(
                    Bytes.toString(cloneFamily)+","+
                            Bytes.toString(qualifier)+","+
                            Bytes.toString(value)
            );
        }
        table.close();
    }
    /**
     * 获取多row通过范围(scan)
     * @param tableName
     * @throws IOException
     */
    public static void getRowsByRowRange(String tableName,String startRowkey,String stopRowkey) throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(startRowkey));
        scan.setStopRow(Bytes.toBytes(stopRowkey));
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                byte[] bytes = CellUtil.cloneRow(cell);
                byte[] cloneFamily = CellUtil.cloneFamily(cell);
                byte[] qualifier = CellUtil.cloneQualifier(cell);
                byte[] value = CellUtil.cloneValue(cell);
                System.out.println(
                        Bytes.toString(bytes)+","+
                        Bytes.toString(cloneFamily)+","+
                        Bytes.toString(qualifier)+","+
                        Bytes.toString(value)
                );
            }
        }
        scanner.close();
        table.close();
    }
    /**
     * 使用过滤器查询
     * @param tableName
     * @throws IOException
     */
    public static void getRowsByRowName(String tableName,String family,String column,String value) throws IOException {

        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        //过滤器
        SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(column), CompareFilter.CompareOp.EQUAL, Bytes.toBytes(value));
        //没有过滤的列，直接过滤掉
        filter.setFilterIfMissing(true);
        //todo 多个filter拼接
//        SingleColumnValueFilter filter1 = new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(column), CompareFilter.CompareOp.EQUAL, Bytes.toBytes(value));
//        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
//        filterList.addFilter(filter);
//        filterList.addFilter(filter1);
//        scan.setFilter(filterList);
        scan.setFilter(filter);
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                byte[] bytes = CellUtil.cloneRow(cell);
                byte[] cloneFamily = CellUtil.cloneFamily(cell);
                byte[] qualifier = CellUtil.cloneQualifier(cell);
                byte[] valueStr = CellUtil.cloneValue(cell);
                System.out.println(
                        Bytes.toString(bytes)+","+
                                Bytes.toString(cloneFamily)+","+
                                Bytes.toString(qualifier)+","+
                                Bytes.toString(valueStr)
                );
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
}

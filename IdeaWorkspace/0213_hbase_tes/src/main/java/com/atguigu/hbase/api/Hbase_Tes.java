package com.atguigu.hbase.api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class Hbase_Tes {
    private static Connection connection=null;

    static {
       Configuration conf = HBaseConfiguration.create();
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
     *创建数据库
     *
     */
    public  void createDb(String dbName) throws Exception {
        Admin admin = connection.getAdmin();

        NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create("ste2").build();
        admin.createNamespace(namespaceDescriptor);
        admin.close();
    }

    /**
     * 创键表
     */
    public void createTable(String tableName,String family) throws IOException {
        Admin admin = connection.getAdmin();
        if (admin.tableExists(TableName.valueOf(tableName))){
            return;
        }
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(Bytes.toBytes(family));
        hTableDescriptor.addFamily(hColumnDescriptor);
        admin.createTable(hTableDescriptor);

        admin.close();
    }

    /**
     * 删除表
     */
    public void alterTable(String tableName,String value) throws IOException {
        Admin admin = connection.getAdmin();
        if (admin.tableExists(TableName.valueOf(tableName))) {
        return;
        }
        admin.disableTable(TableName.valueOf(tableName));
        admin.deleteTable(TableName.valueOf(tableName));
        admin.close();
    }

    /**
     * 修改表
     */
    public void modifyTable(String tableName,String family,int value) throws IOException {
        Admin admin = connection.getAdmin();
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(Bytes.toBytes(family));
        hColumnDescriptor.setMaxVersions(value);
        admin.modifyColumn(TableName.valueOf(tableName),hColumnDescriptor);
        admin.close();
    }
    /**
     * 插入一列数据
     */
    public void putRow(String tableName,String  rowKey,String family,String column,String value) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes(value));
        table.put(put);
        table.close();
    }
    /**
     * 插入一列数据
     */
    /**
     * 查询行
     */
    public void getRow(String tableName,String  rowKey) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        Result result = table.get(get);
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            System.out.println(Bytes.toString(CellUtil.cloneFamily(cell)) +Bytes.toString( CellUtil.cloneRow(cell)) + Bytes.toString(CellUtil.cloneValue(cell)));
        }

        table.close();
    }

    /**
     * 查询列
     */
    public void getCell(String tableName,String  rowKey,String family,String column) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addColumn(Bytes.toBytes(family),Bytes.toBytes(column));
        Result result = table.get(get);
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            System.out.println(Bytes.toString(CellUtil.cloneFamily(cell)) +Bytes.toString( CellUtil.cloneRow(cell)) + Bytes.toString(CellUtil.cloneValue(cell)));
        }

        table.close();
    }
    /**
     * 通过scan  startRow stopRow 获取行的值
     */
    public void getRows(String tableName,String startRow,String stopRow) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan(Bytes.toBytes(startRow), Bytes.toBytes(stopRow));
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                System.out.println(Bytes.toString(CellUtil.cloneFamily(cell)) +Bytes.toString( CellUtil.cloneRow(cell)) + Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
        resultScanner.close();
        table.close();
    }
    /**
     * 通过scan  startRow stopRow 获取列的值
     */
    public void getCell(String tableName,String startRow,String stopRow,String family,String column) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan(Bytes.toBytes(startRow), Bytes.toBytes(stopRow));
        scan.addColumn(Bytes.toBytes(family),Bytes.toBytes(column));
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                System.out.println(Bytes.toString(CellUtil.cloneFamily(cell)) +Bytes.toString( CellUtil.cloneRow(cell)) + Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
        resultScanner.close();
        table.close();
    }
    /**
     * 通过fiter查询
     *
     */
    public void getCellByFilter(String tableName,String prefix) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        scan.setRowPrefixFilter(Bytes.toBytes(prefix));
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                System.out.println(Bytes.toString(CellUtil.cloneFamily(cell)) +Bytes.toString( CellUtil.cloneRow(cell)) + Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
        resultScanner.close();
        table.close();
    }
    /**
     * 删除行
     */
    public void deleteRow(String tableName,String  rowKey) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);
        table.close();
    }
    /**
     * 删除列(最新版本)
     */
    public void deleteCell(String tableName,String  rowKey,String family,String column) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        delete.addColumn(Bytes.toBytes(family),Bytes.toBytes(column));
        table.delete(delete);
        table.close();
    }
    /**
     * 删除所有版本列
     *
     */
    public void deleteCells(String tableName,String  rowKey,String family,String column) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        delete.addColumns(Bytes.toBytes(family),Bytes.toBytes(column));
        table.delete(delete);
        table.close();
    }
}

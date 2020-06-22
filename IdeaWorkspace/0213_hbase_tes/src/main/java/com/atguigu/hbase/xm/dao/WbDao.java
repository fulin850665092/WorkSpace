package com.atguigu.hbase.xm.dao;

import com.atguigu.hbase.xm.constan.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.XAttr;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.BytesWritable;
import sun.tools.jconsole.Tab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WbDao {

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
     * 创建namespace
     */
    public void createNameSapce() throws Exception {
        Admin admin = connection.getAdmin();

        try {
            //判断是否存在
            NamespaceDescriptor namespaceDescriptor1 = admin.getNamespaceDescriptor(Names.NAMESPACE_WEIBO);
        } catch (IOException e) {

            NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(Names.NAMESPACE_WEIBO).build();
            admin.createNamespace(namespaceDescriptor);

        }finally {
            admin.close();
        }

    }

    /**
     * 创建表
     * @param tableName
     * @param version
     * @param Familys
     * @throws Exception
     */
    public void createTable(String tableName,int version, String... Familys) throws  Exception{
        Admin admin = connection.getAdmin();
        if (admin.tableExists(TableName.valueOf(tableName))){
            System.err.println("table：" + tableName + "已经存在！");
            admin.close();
            return;
        }
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        for (String familyName: Familys) {
            HColumnDescriptor descriptor = new HColumnDescriptor(Bytes.toBytes(familyName));
            descriptor.setMaxVersions(version);
            hTableDescriptor.addFamily(descriptor);
        }
        admin.createTable(hTableDescriptor);
        admin.close();
    }

    /**
     * 插入一行数据
     * @param tableName
     * @param rowKey
     * @param familyName
     * @param column
     * @param content
     * @throws Exception
     */
    public void putCell(String tableName, String rowKey, String familyName, String column, String content)throws Exception {
        Table table = connection.getTable(TableName.valueOf(tableName));

        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(familyName),Bytes.toBytes(column),Bytes.toBytes(content));
        table.put(put);
        table.close();
    }

    /**
     * 根据前缀获取数据
     * @param tableName
     * @param prefix
     * @return
     * @throws Exception
     */
    public List<String> getRowKeysByPreFix(String tableName, String prefix) throws Exception{
        List<String> list = new ArrayList<String>();
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        scan.setRowPrefixFilter(Bytes.toBytes(prefix));
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            //每行一个 rowkey
            list.add(Bytes.toString(result.getRow()));
        }
        resultScanner.close();
        table.close();

        return list;
    }

    /**
     * 批量插入数据
     * @param tableName
     * @param rowKeys
     * @param family
     * @param column
     * @param value
     * @throws Exception
     */
    public void putCells(String tableName, List<String> rowKeys, String family, String column, String value) throws Exception {

        if (rowKeys.size() == 0){
            return;
        }
        Table table = connection.getTable(TableName.valueOf(tableName));
        //批量put
        List<Put> puts= new ArrayList<Put>();
        for (String rowKey : rowKeys) {
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(family), Bytes.toBytes(column), Bytes.toBytes(value));
            puts.add(put);
        }
        table.put(puts);
        table.close();
    }

    /**
     * 根据rowKey范围获取数据
     * @param tableName
     * @param startRow
     * @param stopRow
     * @return
     * @throws Exception
     */
    public List<String> getRowKeysByRange(String tableName, String startRow, String stopRow)throws Exception {
        List<String> list = new ArrayList<String>();
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan(Bytes.toBytes(startRow),Bytes.toBytes(stopRow));
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            //每行一个 rowkey
            list.add(Bytes.toString(result.getRow()));
        }
        resultScanner.close();
        table.close();

        return list;
    }

    /**
     * 删除行
     * @param tableName
     * @param rowKey
     * @throws Exception
     */
    public void deleteRow(String tableName, String rowKey)throws Exception {

        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);
        table.close();

    }

    /**
     * 删除所有版本的列
     * @param tableName
     * @param rowKey
     * @param family
     * @param column
     * @throws Exception
     */
    public void deleteColunm(String tableName, String rowKey, String family, String column) throws  Exception{
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        //删除所有版本
        delete.addColumns(Bytes.toBytes(family), Bytes.toBytes(column));
        table.delete(delete);
        table.close();
    }

    /**
     * 根据rowKey的前缀获取一列
     * @param tableName
     * @param prefix
     * @param family
     * @param column
     * @return
     */
    public List<String> getCellsbByPerfix(String tableName, String prefix, String family, String column)throws Exception {
        List<String> list= new ArrayList<String>();
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        scan.setRowPrefixFilter(Bytes.toBytes(prefix));
        scan.addColumn(Bytes.toBytes(family),Bytes.toBytes(column));
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            Cell cell = result.rawCells()[0];
            list.add(Bytes.toString(CellUtil.cloneValue(cell)));
        }
        resultScanner.close();
        table.close();
        return  list;
    }

    /**
     * 获取行
     * @param tableName
     * @param rowkey
     * @return
     * @throws Exception
     */
    public List<String> getRow(String tableName, String rowkey) throws  Exception {
        List<String> list= new ArrayList<String>();
        Table table = connection.getTable(TableName.valueOf(tableName));

        Get get = new Get(Bytes.toBytes(rowkey));
        get.setMaxVersions();
        Result result = table.get(get);
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            list.add(Bytes.toString(CellUtil.cloneValue(cell)));
        }

        table.close();
        return list;
    }

    /**
     * 批量获取列
     * @param tableName
     * @param rowKeys
     * @param family
     * @param column
     * @return
     * @throws Exception
     */
    public List<String> getCellByRowkeys(String tableName, List<String> rowKeys, String family, String column) throws  Exception{
        List<String> list= new ArrayList<String>();
        Table table = connection.getTable(TableName.valueOf(tableName));

        List<Get> gets = new ArrayList<Get>();

        for (String rowKey : rowKeys) {
            Get get = new Get(Bytes.toBytes(rowKey));
            gets.add(get);
        }
        Result[] results = table.get(gets);
        for (Result result : results) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                list.add(Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
        return list;
    }

}

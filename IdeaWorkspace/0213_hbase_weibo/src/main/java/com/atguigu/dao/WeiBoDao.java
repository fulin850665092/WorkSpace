package com.atguigu.dao;

import com.atguigu.constant.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeiBoDao {

    public  static Connection connection=null;
    static {

        try {
            Configuration configuration = HBaseConfiguration.create();
            configuration.set("hbase.zookeeper.quorum","hadoop102,hadoop103,hadoop104");
            connection= ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *  //创建namespace
     * @param namespaceWeibo
     * @throws IOException
     */
    public void createNameSpace(String namespaceWeibo) throws IOException {
        Admin admin = connection.getAdmin();
        try {
            //判断namespace是不是存在，不存在则有异常
            admin.getNamespaceDescriptor(namespaceWeibo);
        } catch (NamespaceNotFoundException e) {
            //不存在创建
            NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(namespaceWeibo).build();

            admin.createNamespace(namespaceDescriptor);
        }finally {
            admin.close();
        }

    }

    /**
     * 创建表
     * @param tableName
     * @param familys
     * @throws IOException
     */
    public void createTable(String tableName, Integer versions, String...familys) throws  IOException {
        Admin admin = connection.getAdmin();
        boolean exists = admin.tableExists(TableName.valueOf(tableName));
        if (exists){
            System.out.println("table"+tableName+"already exists!");
            admin.close();
            return;
        }
        HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        for (String familyDatum : familys) {
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(familyDatum);
            hColumnDescriptor.setMaxVersions(versions);
            tableDescriptor.addFamily(hColumnDescriptor);
        }
        admin.createTable(tableDescriptor);
        admin.close();
    }

    /**
     * 微博表插入一条数据
     * @param tableName
     * @param rowKey
     * @param family
     * @param column
     * @param value
     * @throws IOException
     */
    public void putCell(String tableName, String rowKey, String family, String column, String value) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes(value));
        table.put(put);
        table.close();
    }

    /**
     * 根据明星id查询所有的粉丝id
     * @param tableName
     * @param prefix
     * @return
     */
    public List<String> getRowKeysByPrefix(String tableName, String prefix) throws IOException {
        List<String> list= new ArrayList<String>();
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        //过滤器，获取前缀为prefix的列
        scan.setRowPrefixFilter(Bytes.toBytes(prefix));
        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            //一行一个rowKey
            list.add(Bytes.toString(result.getRow()));
        }
        resultScanner.close();
        table.close();
        return  list;
    }

    /**
     * 往inbox表中插入明星微博id
     * @param tableName
     * @param rowkeys
     * @param family
     * @param column
     * @param value
     */
    public void  putCells(String tableName, List<String> rowkeys, String family, String column, String value) throws IOException {
        if (rowkeys.size()==0){
            return;
        }
        Table table = connection.getTable(TableName.valueOf(tableName));
        //批量插入
        List<Put> putList= new ArrayList<Put>();
        for (String rowkey : rowkeys) {
            Put put = new Put(Bytes.toBytes(rowkey));
            put.addColumn(Bytes.toBytes(family),Bytes.toBytes(column),Bytes.toBytes(value));
            putList.add(put);
        }
        table.put(putList);
        table.close();
    }

    public List<String> getRowKeysByRange(String tableName, String startRow, String stopRow) throws IOException {
        List<String> list= new ArrayList<String>();
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan(Bytes.toBytes(startRow),Bytes.toBytes(stopRow));
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            //一行一个rowKey
            list.add(Bytes.toString(result.getRow()));
        }
        scanner.close();
        table.close();
        return  list;
    }

    /**
     * 删除行
     * @param tableName
     * @param rowKey
     */
    public void deleteRow(String tableName, String rowKey) throws IOException {
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
     * @param colunm
     * @throws IOException
     */
    public void deleteColunm(String tableName, String rowKey, String family, String colunm) throws IOException {
        Table table = connection.getTable(TableName.valueOf(Names.TABLE_INBOX));

        Delete delete = new Delete(Bytes.toBytes(rowKey));
        delete.addColumns(Bytes.toBytes(family),Bytes.toBytes(colunm));

        table.delete(delete);
        table.close();
    }

    /**
     * 查询关注明星的所有微博
     * @param tableName
     * @param prefix
     * @param family
     * @param colunm
     * @return
     */
    public List<String> getCellsbByPerfix(String tableName, String prefix, String family, String colunm) throws IOException {
        List<String> list=new ArrayList<String>();
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        scan.addColumn(Bytes.toBytes(family),Bytes.toBytes(colunm));
        scan.setRowPrefixFilter(Bytes.toBytes(prefix));
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                list.add(Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
        scanner.close();
        table.close();
        return  list  ;
    }
    /**
     * 获取某个fans关注的所有star的近期微博id
     * @param rowKey
     * @return
     */
    public List<String> getRow(String tableName, String rowKey) throws IOException {
        List<String> list= new ArrayList<String>();
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        //获取多少版本
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
     * 获取某个fans关注的所有star的近期微博
     * @param tableName
     * @param rowkeys
     * @param family
     * @param colunm
     * @return
     */
    public List<String> getCellsByRowKeys(String tableName, List<String> rowkeys, String family, String colunm) throws IOException {
        List<Get> getList= new ArrayList<Get>();
        List<String> weiboList= new ArrayList<String>();
        Table table = connection.getTable(TableName.valueOf(tableName));
        //批量查询封装
        for (String rowkey : rowkeys) {
            Get get = new Get(Bytes.toBytes(rowkey));
            get.addColumn(Bytes.toBytes(family),Bytes.toBytes(colunm));
            getList.add(get);
        }
        Result[] results = table.get(getList);
        //获取微博内容
        for (Result result : results) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                weiboList.add(Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
        return  weiboList;
    }
}

package com.atguigu.hbase.api2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import sun.tools.jconsole.Tab;

import java.io.IOException;


public class HbaseApiDDL {

    //简单的单列模式（用静态代码块实现）
    private static Connection connection=null;

    static {
        Configuration configuration = HBaseConfiguration.create();
        //设置zookeeper集群地址，就可以获取hbase所有的rs的所有信息
        configuration.set("hbase.zookeeper.quorum","hadoop102,hadoop103,hadooop104");
        try {
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建表
     * @param tableName
     * @param familys
     * @throws IOException
     */
   public  static  void create(String tableName,String...familys) throws IOException {

        Admin admin = connection.getAdmin();
        if (admin.tableExists(TableName.valueOf(tableName))){
            System.err.println("表："+tableName+"已经存在！");
            admin.close();
            return;
        }
        // 表的描述器
        HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        for (String family : familys) {
           //列族的描述器
           HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(family);
           tableDescriptor.addFamily(hColumnDescriptor);
       }

        admin.createTable(tableDescriptor);
        admin.close();
    }

    /**
     * 修改列族（版本数）
     * @param tableName
     * @param family
     * @throws IOException
     */
    public  static  void modifyTable(String tableName,String family) throws IOException {
        Admin admin = connection.getAdmin();
        //列族描述器
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(family);
        hColumnDescriptor.setMaxVersions(3);
        admin.modifyColumn(TableName.valueOf(tableName),hColumnDescriptor);
        admin.close();
   }
    /**
     * 修改列族（增加列族）
     * @param tableName
     * @param family
     * @throws IOException
     */
    public  static  void modifyTable1(String tableName,String family) throws IOException {
        Admin admin = connection.getAdmin();
        //列族描述器
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(family);
        admin.addColumn(TableName.valueOf(tableName),hColumnDescriptor);

        admin.close();
    }

    /**
     * 删除表
     * @param tableName
     * @throws IOException
     */
    public  static void dropTable(String tableName) throws IOException {
        Admin admin = connection.getAdmin();
        if (!admin.tableExists(TableName.valueOf(tableName))){
            System.out.println("表不存在！");
            admin.close();
            return;
        }
        admin.disableTable(TableName.valueOf(tableName));
        admin.deleteTable(TableName.valueOf(tableName));
        admin.close();
    }



    public static void main(String[] args) throws IOException {
//        create("stu2","info");
//        modifyTable("stu1","info");
        modifyTable1("stu1","age");
//        dropTable("stu2");
    }

}

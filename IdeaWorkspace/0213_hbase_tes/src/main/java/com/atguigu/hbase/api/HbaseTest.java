package com.atguigu.hbase.api;

import java.io.IOException;

public class HbaseTest {
    public static void main(String[] args) throws IOException {

//        HBaseUtil_TES.createTable("tes1","info");
//        HBaseUtil_TES.modifyTable("tes1","info",3);
//        HBaseUtil_TES.dropTable("tes1");
//        HBaseUtil_TES.putCell("tes1","1004","info","name","ls");
        HBaseUtil_TES.getRow("tes1","1003");
//        HBaseUtil_TES.getCell("tes1","1001","info","name");
//        HBaseUtil_TES.getRowsByRowRange("tes1","1001","1004");
//        HBaseUtil_TES.getRowsByRowName("tes1","info","name","ls");
//        HBaseUtil_TES.deleteRow("tes1","1001");
//        HBaseUtil_TES.deleteCloumn("tes1","1002","info","name");

    }

}

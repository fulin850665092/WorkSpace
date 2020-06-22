package com.atguigu.hbase.xm;

import com.atguigu.hbase.xm.controller.WbController;

public class WbApp {

    private static WbController wbController= new WbController();

    public static void main(String[] args) throws Exception {

        //初始化表
        wbController.init();
    }
}

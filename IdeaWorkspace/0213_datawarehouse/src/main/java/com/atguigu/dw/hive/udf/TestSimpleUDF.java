package com.atguigu.dw.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 只能处理 简单的数据类型
 */
public class TestSimpleUDF extends UDF {
    public String evaluate(String args){
        return "";
    }
}

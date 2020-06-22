package com.atguigu.hive;


import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.List;
/**
 * hive(default)> select myudtf("hello,world,hadoop,hive", ",");
 *
 * hello
 * world
 * hadoop
 * hive
 */
/**
 * 自定义 一进多出函数，类似于explode炸开函数
 */
public class NewUdtf extends GenericUDTF {

    List<String> list=new ArrayList<String>();
    /**
     * 约定输出的列的名字和 列的类型
     * @param argOIs
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        //约定列的类型
        ArrayList<String> fieldNames = new ArrayList();
        //约定列的数据类型
        ArrayList<ObjectInspector> fieldOIs = new ArrayList();

        //列名
        fieldNames.add("word");
        //列数据类型
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs);
    }

    //业务逻辑
    @Override
    public void process(Object[] objects) throws HiveException {
        if (objects.length<2){
            throw new HiveException("Input Args Length Exception!!");
        }
        //获取待处理的数据
        String line = objects[0].toString();
        String spilt = objects[1].toString();
        String[] split = line.split(spilt);
        for (String str : split) {
            //复用list，写之前先清空，要清空
            list.clear();
            list.add(str);
            forward(list);
        }

    }

    @Override
    public void close() throws HiveException {

    }
}


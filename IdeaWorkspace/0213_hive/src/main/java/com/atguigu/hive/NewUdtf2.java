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
 * hive(default)> select myudtf("hello_hhh,world__ttt,hadoop_rrr,hive_hhh", ",");
 *
 * hello  hhh
 * world  ttt
 * hadoop  rrr
 * hive    hhh
 */

/**
 * 自定义 一进多出函数，类似于explode炸开函数
 */
public class NewUdtf2 extends GenericUDTF {

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
        fieldNames.add("word1");
        //列数据类型
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs);
    }

    //业务逻辑
    @Override
    public void process(Object[] objects) throws HiveException {
        if (objects.length<3){
            throw new HiveException("Input Args Length Exception!!");
        }
       // hello_hhh,world__ttt,hadoop_rrr,hive_hhh
        //获取待处理的数据
        String line = objects[0].toString();
        String spilt1 = objects[1].toString();
        String spilt2 = objects[2].toString();
        //hello_hhh
        String[] split = line.split(spilt1);
        for (String str1 : split) {
            //hello hhh
            //清空集合
            list.clear();
            String[] split2 = str1.split(spilt2);
            for (String str2 : split2) {
                //复用list，写之前先清空，要清空
                list.add(str2);
            }
            //写出
            forward(list);
        }

    }

    @Override
    public void close() throws HiveException {

    }
}

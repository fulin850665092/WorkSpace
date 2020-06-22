package com.atguigu.hive;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;


public class NewUdf extends GenericUDF {

    //对输入参数的判断处理和返回值的类型的一个约定
    @Override
    public ObjectInspector initialize(ObjectInspector[] objectInspectors) throws UDFArgumentException {
       //参数类型判断限制
        if (objectInspectors.length!=1&&objectInspectors==null){
            throw new UDFArgumentLengthException("Input Args Length Error!!");
        }
        if (!objectInspectors[0].getCategory().equals(ObjectInspector.Category.PRIMITIVE)){
            throw  new UDFArgumentTypeException(0,"Input Args Type Error!!");
        }
        //约定函数的返回值类型为int
        return PrimitiveObjectInspectorFactory.javaIntObjectInspector;
    }
    //函数逻辑处理
    @Override
    public Object evaluate(DeferredObject[] deferredObjects) throws HiveException {
        Object o = deferredObjects[0].get();
        if (o==null){
            return 0;
        }
        return o.toString().length();
    }

    @Override
    public String getDisplayString(String[] strings) {
        return null;
    }
}

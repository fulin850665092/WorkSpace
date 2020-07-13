package com.atguigu.dw.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonArrayToStructArray2 extends GenericUDF {
    /**
     * 检测入参
     * 设置出参
     * @param arguments
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
        //判断长度
        if (arguments.length<3) {
            throw new UDFArgumentException("参数长度必须大于3！");
        }
        //判断类型
        for (int i = 0; i < arguments.length; i++) {
            if ("string".equals(arguments[i].getTypeName())) {
                throw new UDFArgumentException("参数类型必须为string！");
            }
        }
        //输出封装 Array(struct(),struct()...)
        //字段
        List<String> fields=new ArrayList<>();
        List<ObjectInspector> fieldOIs=new ArrayList<>();
        //
        for (int i = (arguments.length-1)/2+1; i <arguments.length ; i++) {
            fields.add(getConstantStringValue(arguments,i).split(":")[0]);
            String type = getConstantStringValue(arguments, i).split(":")[1];
            switch (type){
                case "string":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
                    break;
                case "boolean":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaBooleanObjectInspector);
                    break;
                case "tinyint":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaByteObjectInspector);
                    break;
                case "smallint":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaShortObjectInspector);
                    break;
                case "int":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaIntObjectInspector);
                    break;
                case "bigint":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaLongObjectInspector);
                    break;
                case "float":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaFloatObjectInspector);
                    break;
                case "double":
                    fieldOIs.add(PrimitiveObjectInspectorFactory.javaDoubleObjectInspector);
                    break;
                default:
                    throw new UDFArgumentException("json_array_to_struct_array 不支持" + type + "类型");

            }
        }

        return ObjectInspectorFactory.getStandardListObjectInspector(ObjectInspectorFactory.getStandardStructObjectInspector(fields,fieldOIs));
    }

    @Override
    public Object evaluate(DeferredObject[] arguments) throws HiveException {
        if (arguments[0].get()==null) {
            return null;
        }
        //返回的数据结构 ： List<List<Obj>>
        List<List<Object>> list = new ArrayList<>();

        String jsonStr = arguments[0].get().toString();
        JSONArray jsonArray = new JSONArray(jsonStr);
        for (int i = 0; i < jsonArray.length(); i++) {
            List<Object> struct= new ArrayList<>();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            for (int j = 1; j < 1 + (arguments.length - 1) / 2; j++) {
                if (jsonObject.has(arguments[j].get().toString())) {
                    struct.add(jsonObject.get(arguments[j].get().toString()));
                } else {
                    struct.add(null);
                }
            }
            list.add(struct);

        }
        return list;
    }

    @Override
    public String getDisplayString(String[] children) {
        return getStandardDisplayString("json_array_to_struct_array",children);
    }
}

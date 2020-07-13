package com.atguigu.dw.hive.udf;

import org.apache.hadoop.hive.ql.exec.Description;
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

@Description(name="json_array_to_struct_array",value = "json  array to struct  array.....")
public class JsonArrayToStructArray1 extends GenericUDF {
    /**
     *1.对输入参数的检测
     *2.返回输出的值的对象的检查器
     * @param arguments
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
        //个数检测
        if (arguments.length <3){
            throw new UDFArgumentException("json_array_to_struct_array需要至少3个参数!");
        }
        //类型检测
        for (int i = 0; i < arguments.length; i++) {
            if (!"string".equals(arguments[i].getTypeName())){
                throw new UDFArgumentException("json_array_to_struct_array的第" + (i + 1) + "个参数应为string类型!");
            }
        }
        //输出封装
        List<String> nameFields = new ArrayList<>();    //所有字段
        List<ObjectInspector> fieldOIs = new ArrayList<>();  //每个字段的类型解析器
        String typeName = arguments[0].getTypeName();


        for (int i =(arguments.length-1)/2+1; i <1; i++) {
            String fieldname = getConstantStringValue(arguments,i).split(":")[0];
            nameFields.add(fieldname);
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

        // 返回的数据是一个hive，array。
        return ObjectInspectorFactory
                .getStandardListObjectInspector(ObjectInspectorFactory.getStandardStructObjectInspector(nameFields,fieldOIs));
    }

    /**
     * 对传入的数据做计算，返回函数最终的值。
     * @param arguments
     * @return
     * @throws HiveException
     */
    @Override
    public Object evaluate(DeferredObject[] arguments) throws HiveException {
        List<List<Object>> result= new ArrayList<>();
        if (arguments[0].get() == null) {
            return null;
        }
         //获取传入的json数组
        String jsonStr = arguments[0].get().toString();
        JSONArray jsonArray = new JSONArray(jsonStr);
        for (int i = 0; i <jsonArray.length() ; i++) {
            List<Object> struct= new ArrayList<>();
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            for (int j = 1; j < 1 + (arguments.length - 1) / 2; j++) {
                if (jsonObject.has(arguments[j].get().toString())) {
                    struct.add(jsonObject.get(arguments[j].get().toString()));
                } else {
                    struct.add(null);
                }
            }
            result.add(struct);

        }

        return result;
    }

    /**
     * 返回要展示的字符串
     * @param children
     * @return
     */
    @Override
    public String getDisplayString(String[] children) {
        return getStandardDisplayString("json_array_to_struct_array",children);
    }
}

package com.atguigu.dw.hive.udtf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructField;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

@Description(name="explode_json_array",value = "explode json array ....")
public class ExplodeJsonArray extends GenericUDTF {
    /**
     * 校验参数的个数，和参数的类型
     * @param argOIs
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        //拿到每个字段入参引用的集合
        List<? extends StructField> fieldRefs = argOIs.getAllStructFieldRefs();
        //数据过滤
        if(fieldRefs.size()!=1){
            throw new UDFArgumentException("explode_json_array 函数的参数个数只能为1!");
        }
        //拿到参数的对象检查器的类型的名称
        String typeName = fieldRefs.get(0).getFieldObjectInspector().getTypeName();
        if (!"string".equals(typeName)){
            throw new UDFArgumentException("explode_json_array 函数的参数类型只能为string!");
        }
        //每个列的列名
        ArrayList<String> fieldNames = new ArrayList<String>();
        //每个列的列名对应的对象检查器（每个列的类型）
        ArrayList<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();
        fieldNames.add("item");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames,fieldOIs);
    }

    @Override
    public void process(Object[] args) throws HiveException {
        String jsonStr = args[0].toString();
        JSONArray jsonArray = new JSONArray(jsonStr);

        for (int i = 0; i <jsonArray.length() ; i++) {
            //声明长度为1 的数组
            String [] result= new String[1];
            result[0]=jsonArray.getString(i);
            //传参数的是数组或者是list（可能存在炸裂出来多列数据，有数组的长度决定。）
            forward(result);
        }


    }


    @Override
    public void close() throws HiveException {

    }

}

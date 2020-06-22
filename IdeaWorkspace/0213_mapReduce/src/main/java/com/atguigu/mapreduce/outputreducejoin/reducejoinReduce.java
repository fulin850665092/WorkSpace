package com.atguigu.mapreduce.outputreducejoin;

import com.atguigu.bean.OrderJoinBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class reducejoinReduce extends Reducer<Text, OrderJoinBean,OrderJoinBean,NullWritable> {



    @Override
    protected void reduce(Text key, Iterable<OrderJoinBean> values, Context context) throws IOException, InterruptedException {

        List<OrderJoinBean> list= new ArrayList<OrderJoinBean>();
        OrderJoinBean pdOrder= new OrderJoinBean();

        for (OrderJoinBean bean:values
             ) {
            if (bean.getFlag().equals("order")){
                try {
                    OrderJoinBean orderbean= new OrderJoinBean();
                    BeanUtils.copyProperties(orderbean,bean);
                    list.add(orderbean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    BeanUtils.copyProperties(pdOrder,bean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        //join
        for (OrderJoinBean joinbean:list
             ) {
            joinbean.setPname(pdOrder.getPname());
            context.write(joinbean,NullWritable.get());
        }
    }
}

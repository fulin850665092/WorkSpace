package com.atguigu.shuffer.comparator;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class comparatorOrder extends WritableComparator {
    protected comparatorOrder(){
        super(OrderBean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean orderBean= (OrderBean) a;
        OrderBean orderBean1= (OrderBean) b;
        return  orderBean.getOrder_id().compareTo(orderBean1.getOrder_id());
    }
}

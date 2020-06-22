package com.atguigu.shuffer.patitioner;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ComparableBean implements WritableComparable<ComparableBean> {

    private Long upFlow;
    private Long downFlow;
    private Long sumFlow;

    public ComparableBean() {
    }

    public Long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Long upFlow) {
        this.upFlow = upFlow;
    }

    public Long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Long downFlow) {
        this.downFlow = downFlow;
    }

    public Long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(Long sumFlow) {
        this.sumFlow = sumFlow;
    }

    @Override
    public String toString() {
        return upFlow+"\t"+downFlow+"\t"+sumFlow;
    }

    public int compareTo(ComparableBean o) {
//        int result;
//        if (this.sumFlow>o.getSumFlow()){
//            result=1;
//        }else if (this.sumFlow>o.getSumFlow()){
//            result= -1;
//        }else {
//            result=0;
//        }
//        return result;
        //升序
        return  this.sumFlow.compareTo(o.getSumFlow());
    }


    /**
     *序列化方法
     * @param out
     * @throws IOException
     */
    public void write(DataOutput out) throws IOException {

        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    /**
     * 反序列化方法
     * @param in
     * @throws IOException
     */
    public void readFields(DataInput in) throws IOException {

        this.upFlow=in.readLong();
        this.downFlow=in.readLong();
        this.sumFlow=in.readLong();
    }
    public  void set( ){
        this.sumFlow=this.upFlow+this.downFlow;
    }

}

package com.atguigu.bean;

import org.apache.hadoop.io.WritableComparable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ComparableBean2 implements  WritableComparable<ComparableBean2>{
private Long upFlow;

private Long downFlow ;

private Long sumFlow ;

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

public void setSumFlow() {
        this.sumFlow = this.upFlow + this.downFlow;
        }

@Override
public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
        }



public ComparableBean2(){}

public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
        }

public void readFields(DataInput in) throws IOException {
        upFlow = in.readLong();
        downFlow = in.readLong();
        sumFlow = in.readLong();
        }

/**
 * 比较规则:  按照总流量倒序
 * @param o
 * @return
 */
    public int compareTo(ComparableBean2 o) {
//        return -this.sumFlow.compareTo(o.getSumFlow());
        return  -this.sumFlow.compareTo((o.getSumFlow()))  ;
    }

}

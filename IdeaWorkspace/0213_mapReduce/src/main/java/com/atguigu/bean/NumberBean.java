package com.atguigu.bean;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NumberBean implements Writable {

        private  Long upNum;
        private  Long downNum;
        private  Long sumNum;

     public NumberBean() {
     }

    @Override
        public String toString() {
            return upNum+"\t"+downNum+"\t"+sumNum;
        }

        public Long getUpNum() {
            return upNum;
        }

        public void setUpNum(Long upNum) {
            this.upNum = upNum;
        }

        public Long getDownNum() {
            return downNum;
        }

        public void setDownNum(long downNum) {
            this.downNum = downNum;
        }

        public Long getSumNum() {
            return sumNum;
        }

        public void setSumNum(long sumNum) {
            this.sumNum = sumNum;
        }

        public void write(DataOutput out) throws IOException {
             out.writeLong(upNum);
             out.writeLong(downNum);
             out.writeLong(sumNum);

        }

        public void readFields(DataInput in) throws IOException {
            this.upNum=in.readLong();
            this.downNum=in.readLong();
            this.sumNum=in.readLong();
        }

        public Long set(long upNum,long downNum){
            return  upNum+downNum;
        }
}

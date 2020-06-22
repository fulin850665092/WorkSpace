package com.atguigu.bean;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderJoinBean implements Writable {

    private String id;
    private  String pid;
    private  String pname;
    private  int amount;
    private String flag;

    public OrderJoinBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return id+"\t"+pname+"\t"+amount;
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(pid);
        out.writeUTF(pname);
        out.writeInt(amount);
        out.writeUTF(flag);
    }

    public void readFields(DataInput in) throws IOException {
        id=in.readUTF();
        pid=in.readUTF();
        pname=in.readUTF();
        amount=in.readInt();
        flag=in.readUTF();
    }
}

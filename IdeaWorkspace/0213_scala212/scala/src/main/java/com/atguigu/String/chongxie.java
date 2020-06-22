package com.atguigu.String;

public class chongxie {
    public static void main(String[] args) {
            Persion2 persion2= new User3();
        System.out.println(persion2.sum());
    }
}
class Persion2{
    public  int i=10;
    public int sum(){
        return  getI()+i;
    }
    public int getI() {
        return i;
    }
}
class User3 extends Persion2{
    public  int i=20;
    @Override
    public int getI() {
        return i;
    }
}

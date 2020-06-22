package com.atguigu.String;

public class test {

    public static void main(String[] args) {
        //常量运算，在编译的时候就可以运行
        int a= 10+10;
         int i=1;
         int j=i++;
        System.out.println(i+";"+j);
        int i1=1;
        int j1=++i1;
        System.out.println(i1+";"+j1);
        int i2=0;
        i2=i2++;
        System.out.println(i2);
        System.out.println(test(5));
    }
    public static int test(int num){
        if(num<1){
            return 1;
        }else {
            return num*test(num-1);
        }
    }
}



package com.atguigu.String;

import com.atguigu.scala.StringTest;
import com.atguigu.scala.StringTest$;

import java.lang.reflect.Field;

public class Stringtset1 {
    public  static  int age=20;
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //地址值不可变，内容可变（反射）
        String str=" a b ";
        //产生了新的字符串
//        String trim = str.trim();
//        System.out.println("!"+str+"!");
        Class<? extends String> aClass = str.getClass();
        Field field = aClass.getDeclaredField("value");
        field.setAccessible(true);
        char[] s = (char[]) field.get(str);
        s[2]='d';
        System.out.println(str);
        StringTest.getVal();
        StringTest.$colon$greater$percent();
        StringTest$.MODULE$.$colon$greater$percent();
        char  c ='A'+1;
        System.out.println(c);

    }
    public  static void getVal(){
        System.out.println("我是java");
    }
}

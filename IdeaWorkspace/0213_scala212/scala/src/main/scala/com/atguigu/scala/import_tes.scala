//package com.atguigu.scala
//
//import java.sql.{Date => _, _}
//import java.util
//import java.util.{Date => UtilDate}
//object import_tes {
//  def main(args: Array[String]): Unit = {
//    //可以声明在任意位置
//    import  java.util.Date
//    new Date()
//    //scala默认导入类有，scala的类，java.lang下的类，predef下的类
//
//    //scala 中 可以导包
//    import  java.sql
//    new sql.Date()
//    //可以包下导入所有类
//    import java.util._
//    new HashMap[String]()
//    //可以导一个包里面的多个类
//    import java.util.{ArrayList,HashMap}
//    new HashMap[String]()
//    new ArrayList[String]()
//    //可以屏蔽类
//    new Date()
//    val date=new UtilDate()
//    //可以起别名
//    import  java.util.{HashSet=>UtilHashSet}
//    new UtilHashSet[String]()
//    //type  也可以起别名
//    type UtilAbstractMap =java.util.AbstractMap[String,String]
//    val abstractMap=new UtilAbstractMap[String,String]
//    // scala 默认import 是 按照包的相对路径进行导入的
//      //双亲委派不起作用
//        //如果不想使用相对路径，那么可以采取特殊的路径（root）访问
//    new _root_.java.util.HashMap[String]()
//  }
////}
////package java{
////  package util{
////    class HashMap{
////      println("xxxxxx")
////    }
////  }
////}
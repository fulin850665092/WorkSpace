package com.atguigu.scala

import java.util.{HashMap,ArrayList,LinkedHashMap}
//import java.util
//import java.util._

object imp {
  def main(args: Array[String]): Unit = {
    //import：导入指定包中的类
     new ArrayList()
     import  java.util.Date
     new Date()
     //scala 中三个默认导入的包，java.lang Predef scala
     new HashMap()
     new ArrayList()
     new LinkedHashMap()
  }
}

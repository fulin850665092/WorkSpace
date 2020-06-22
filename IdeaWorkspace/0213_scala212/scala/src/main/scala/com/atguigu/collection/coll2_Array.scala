package com.atguigu.collection

import scala.collection.mutable.ArrayBuffer

object coll2_Array {
  def main(args: Array[String]): Unit = {

    //采用的说apply方法
    val array=Array(1,2,3,4)
//    println(array.mkString(","))

    //可变数组
      //构建
    val a1=new ArrayBuffer[Int](16)
    val a2 = ArrayBuffer(1,2,3,4,5)
//    println(a1.mkString(","))
//    println(a2.mkString(","))
      //使用
    //添加
    a1.append(1,2,3,4)
    a1.insert(1,5)

    //修改
    a1.update(1,8)
    a1(1)=9

    //删除
//    a1.remove(1)
//    a1.remove(1,3)

    //查询
    a1(2)
    a1.foreach(println(_))
    println(a1.mkString(","))
    println(a2.mkString(","))
  }
}

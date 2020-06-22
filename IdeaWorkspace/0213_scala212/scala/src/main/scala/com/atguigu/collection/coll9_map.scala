package com.atguigu.collection

import scala.collection.mutable

object coll9_map {
  def main(args: Array[String]): Unit = {

    //映射，map,默认不可变
    val map = Map("a"->1,"b"->2,"c"->3)
    val map1 = map + ("d"->4)
    println(map1 eq map)
    println(map)
    //可变map,无序
    val mutMap = mutable.Map("a"->1,"b"->2,"c"->3)
    mutMap.put("d",4)
    mutMap.update("d",5)
    mutMap.remove("d")
    //map转为有序的集合
    val list = mutMap.toList
    //
    val set = mutMap.toSet

    val iterator = mutMap.keys.iterator
    while (iterator.hasNext){
      val key = iterator.next()
      val value = mutMap.get(key)
      if (value.isEmpty){
        println("查询不存在,给默认值"+value.getOrElse(10))
      }else{
        println(value.get)
      }
    }
    val maybeInt = mutMap.get("d")
    println(maybeInt)
    if (maybeInt.isEmpty){
      println("查询不存在,给默认值"+maybeInt.getOrElse(10))

    }else{
      println(maybeInt.get)
    }
    //上面太 啰嗦。这个方法，取不到就设置默认值为10
    val value = mutMap.getOrElse("d",10)
    println(value)
    println(mutMap)
  }
}

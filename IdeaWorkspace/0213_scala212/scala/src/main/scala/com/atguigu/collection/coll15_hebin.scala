package com.atguigu.collection

import scala.collection.mutable

object coll15_hebin {
  //折叠
  def main(args: Array[String]): Unit = {
    val map1 = mutable.Map("a" -> 1, "b" -> 2, "c" -> 3)
    val map2 = mutable.Map("a" -> 4, "b" -> 5, "c" -> 6)

    println(map1.foldLeft(map2)((map, kv) => {
      val k = kv._1
      val v = kv._2
      //更新值
      map.update(kv._1, map.getOrElse(kv._1, 0) + kv._2)
      map
    }))
    println("map1:"+map1)
    println("===============")
    println("map2:"+map2)
    println("===============")
    println(map1.foldLeft(map2)((z,kv) => {
      //更新值
      z.update(kv._1,z.getOrElse(kv._1,0)+kv._2)
      z
    }))

    val map3 = Map("a" -> 1, "b" -> 2, "c" -> 3)
    val map4 =Map("a" -> 4, "b" -> 5, "c" -> 6)
    println(map3.foldLeft(map4)((z, kv) => {
      //还是得转化成不变Map操作数据
      val z1 = mutable.Map(z.toSeq:_*)
      z1.update(kv._1, z.getOrElse(kv._1, 0) + kv._2)
      z1.toMap
    }))
  }
}

package com.atguigu.collection

import scala.collection.mutable

object coll11_map1 {
  def main(args: Array[String]): Unit = {
    val map2 = Map("a"->1,"b"->2,"c"->3)
    println(map2.get("a").get)
    map2.foreach(t=>{
      println(t._1+"="+t._2)
    })
    //m
    val map = mutable.Map("a"->1,"b"->2,"c"->3)
//    map.foreach(println)
    map.put("d",4)
    map.update("a",6)
    map.remove("d")
    //拿到的keywe为None时，get 不到值 设置默认值为 -1
      //解决空指针问题
    println(map.get("f").getOrElse(-1))
    //todo 直接使用它
    println(map.getOrElse("a", -1))
    println(map.filterKeys(_ == "a"))
    println(map.mkString(","))
    println(map.filterKeys(_ == "a"))
    //队列，可变
    val que = new mutable.Queue[String]()
    que.enqueue("1","2","3")
    val que3 = que += "4"
    println(que)
    println(que eq que3)
  }
}

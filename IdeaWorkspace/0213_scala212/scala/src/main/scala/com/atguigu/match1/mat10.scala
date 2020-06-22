package com.atguigu.match1

object mat10 {
  def main(args: Array[String]): Unit = {
    val list = List(
      "hello spark", "hello scala hive"
    )
    println(
       list
        .flatMap(_.split(" "))
        .groupBy(s=>s)
        .map(s=>(s._1,s._2.size))
        .toList
        .sortBy(_._2)(Ordering.Int.reverse)
    )
    val list1 = List(1, 2, List(2, 4))
    println(list1)
  }

}



package com.atguigu.lianxi

object kaoshi5 {
  def main(args: Array[String]): Unit = {
    val list = List(
      ("hello", 4),
      ("hello spark", 3),
      ("hello spark scala", 2),
      ("hello spark scala hive", 1)
    )
    println(list.flatMap(x=> x._1.split(" ").map((_,x._2)))
      .groupBy(_._1)
        .toList
        .map(s=>(s._1,s._2.map(_._2.toInt).sum))
        .sortBy(_._2)(Ordering.Int.reverse)
        .take(3)
        .map(s=>s._1+":"+s._2)
          )
  }
}

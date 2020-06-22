package com.atguigu.lianxi

object kaoshi3 {
  def main(args: Array[String]): Unit = {
    val list = List(
      ("hello", 4),
      ("hello spark", 3),
      ("hello spark scala", 2),
      ("hello spark scala hive", 1)
    )
   val result=list.map(s=>(s._1+" ").*(s._2))
      .flatMap(s=>s.split(" "))
      .groupBy(s=>s)
      .map(s=>(s._1,s._2.size)).toList
      .sortBy(_._2)(Ordering.Int.reverse)
      .take(3)
      .map(s=>s._1+":"+s._2)

    println(result)

  }
}

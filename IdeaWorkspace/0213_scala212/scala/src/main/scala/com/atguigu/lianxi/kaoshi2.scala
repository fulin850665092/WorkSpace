package com.atguigu.lianxi

import scala.collection.mutable

object kaoshi2 {
    //将上面的数据进行WordCount后排序取前三名！
    //        2. 使用2种不同的方式。
    def main(args: Array[String]): Unit = {
      val list = List(
        ("hello", 4),
        ("hello spark", 3),
        ("hello spark scala", 2),
        ("hello spark scala hive", 1)
      )
      println( list.flatMap(s=>{
        val map = mutable.Map[String,Int]()
        for (elem <- s._1.split(" ").iterator) {
          map.put(elem,s._2)
        }
        map
      })
        .groupBy(_._1)
        .mapValues(_.map(_._2).sum)
        .toList.sortBy(_._2)(Ordering.Int.reverse)
        .take(3)
        .map(s=>s._1+":"+s._2)
      )
    }
}

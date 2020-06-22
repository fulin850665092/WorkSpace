package com.atguigu.lianxi

import scala.collection.mutable.ListBuffer

object kaoshi {
    //将上面的数据进行WordCount后排序取前三名！
    //        2. 使用2种不同的方式。
    def main(args: Array[String]): Unit = {
      val list = List(
        ("hello", 4),
        ("hello spark", 3),
        ("hello spark scala", 2),
        ("hello spark scala hive", 1)
      )
//      val result= ""
      println(list.flatMap(s => {
        val buffer = ListBuffer[String]()
        for (elem <- s._1.split(" ").iterator) {
          buffer.append(elem + ":" + s._2)
        }
        buffer
      })
        .groupBy(s=>s.split(":")(0))
        .map(s=>{
          var sum=0
          for (elem <- s._2) {
            sum+=elem.split(":")(1).toInt
          }
          (s._1,sum)
      })
        .toList.sortBy(_._2)(Ordering.Int.reverse)
        .take(3)
        .map(s=>s._1+":"+s._2)
      )
//      println(result)

    }
}

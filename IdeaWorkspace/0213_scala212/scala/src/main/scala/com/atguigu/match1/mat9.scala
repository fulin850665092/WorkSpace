package com.atguigu.match1

object mat9 {
  def main(args: Array[String]): Unit = {
    val tuple = (1, "zhangsan", 30)
    val (id, name, age) = (1, "zhangsan", 30)
    println(s"id=$id")

    val Array(first, second, _*) = Array(1, 2, 3, 4)
    println(first + "," + second)

    val User(name1, age1) = User("zhangsan", 29)
    println(s"age1=$age1")


    val list = List(
      (1, "hello", 40),
      (2, "hello spark", 30),
      (3, "hello spark scala", 20),
      (4, "hello spark scala hive", 10)
    )
    for ((id3, name3, age3) <- list) {
      println(id3 + "," + name3 + "," + age3 + ";")
    }
    //可以过滤数据
    for ((id3, name3, 30) <- list) {
      println(id3 + "," + name3 + "," + ";")
    }
    val dataMap = Map(
      ("a", 1), ("b", 2), ("c", 3)
    )
    println(dataMap.map(s => (s._1, s._2 * 2))
    )
    println(dataMap.map {
      case (w, d) => {
        (w, d * 2)
      }
    })
    for ((w2, d) <- dataMap) {
      println(w2,d )
    }
    val list2 = List( ("a", 1), ("b", 2), ("c", 3))
    println(list2.map(dt => (dt._1, dt._2 * 2)))
    println( list2.map{
      case (k1, v) => {
        (k1, v * 2)
      }
    })

  }
    case class User(name: String, age: Int)

}



package com.atguigu.lianxi

import scala.io.Source

object coll_wordcount {
  def main(args: Array[String]): Unit = {
    //需求；将文件中单词统计出现的次数并排序取前三名
    val source = Source.fromFile("0213_scala212/input/word.txt").getLines()
    val list = source.toList
    println(list.flatMap(_.split(" ")).map((_, 1)).groupBy(word => word).map(kv =>(kv._1._1, kv._2.size)
    ).toList.sortBy(s=>s._2)(Ordering.Int.reverse).take(3).map(s=>{
      s._1+":"+s._2
    }))

    println(list.flatMap(_.split(" ")).groupBy(word => word).
      map(s=>{(s._1,s._2.size)}).toList.sortBy(s=>s._2)(Ordering.Int.reverse).take(3).map(s=>{
      s._1+":"+s._2
    }))
    println(list.flatMap(_.split(" ")).groupBy(word => word).
      map(s=>{(s._1,s._2.size)}).toList.sortBy(s=>s._2)(Ordering.Int.reverse).take(3).map(s=>{
      s._1+":"+s._2
    }))
  }
}

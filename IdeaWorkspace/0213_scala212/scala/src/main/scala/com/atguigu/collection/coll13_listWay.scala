package com.atguigu.collection

object coll13_listWay {
  def main(args: Array[String]): Unit = {
    val list1 = List(1,2,3,4)
    val list2 = List(3,4,5,6)
    val list3 = List(3,4,5,6,7)
    //合集
    println(list1.union(list2))
    //交集
    println(list1.intersect(list2))
    //差集
    println(list1.diff(list2))
    println(list2.diff(list1))
    //拉链
    println(list1.zip(list2))
    println(list1.zip(list3))
    //自己和自己的索引关联
    println(list3.zipWithIndex)
    val list4 = List("a","b","c")
    println(list4.zipWithIndex)
    val list5 = list2.zip(list1)
    println(list5)
    //解拉链
    println(list5.unzip)
    println("================")
    val list6 = List(1,2,3,4,5,6,7,8,9)
    //滑动窗口，size：窗口大小，step：滑动的步长
    list6.sliding(3, 1).foreach(s=>print(s.sum+","))
    val lis = list6.sliding(3, 1).foreach(s=>s.sum)
  }
}

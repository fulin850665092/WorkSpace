package com.atguigu.collection

object coll4_seq {
  def main(args: Array[String]): Unit = {
      //序列
        //seq=》list
    //构建对象
    val list = List(1,2,3,4)

    //使用
     val list1 = list :+ 1
     val list2 = 1+:list
     val list3 = list.updated(1,4)

     println(list1.mkString(","))
     println(list2.mkString(","))
     println(list3.mkString(","))
     println(list3 eq  list)
  }
}

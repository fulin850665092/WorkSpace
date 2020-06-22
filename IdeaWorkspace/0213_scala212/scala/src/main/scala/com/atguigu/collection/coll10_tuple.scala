package com.atguigu.collection

import scala.collection.mutable

object coll10_tuple {
  def main(args: Array[String]): Unit = {
//    val mutMap = mutable.Map("a"->1,"b"->2,"c"->3)
//
//    //map转为有序的集合
//    val list = mutMap.toList
    //
    //容器：
      //当数据有关系时，封装成Object
        //没关系，但类型相同，可以封装成集合
          //没有关系又是散数据，封装成 Tuple ，当成一个整体来使用
              //todo Tuple：元素的组合，简称：元组,使用小括号将数据封装起来
                  //todo 元组中最多放置的元素最多22个
                      //访问元组：顺序号
    val tuple = (1,"ABC","打的")
    println(tuple)
    println(tuple._1)
    //索引编号访问
    tuple.productElement(0)
    //如果元组中的元素只有2个，称之为对偶元组，也称之为键值对
    val map = Map(("a",1),("b",2),("c",3))
    val list1 = map.toList
    println(list1)
    //就是一个元组
    val tuple1 = "a"->1

  }
}

package com.atguigu.collection

import scala.collection.mutable

object coll14_fold {
  //折叠
  def main(args: Array[String]): Unit = {
    val list1 = List(1, 2, 3, 4, 5)
    // 参数：z:代表初始值
          //(A1,A1)=>A1 :聚合的逻辑
    println(list1.fold(0)(_ + _))
    println(list1.fold(10)(_ - _))
    //从源码角度讲 fold 底层就是 foldleft
    println(list1.foldRight(0)(_ + _))
    println(list1.foldLeft(0)(_ + _))
    //fold,foldRight,foldLeft 的返回值就是 初始值（z）的类型
    println(list1.foldRight("a")(_ + _))
    println(list1.foldLeft("a")(_ + _))
    //返回值字符串类型
    println(list1.foldLeft("")(_ + _))

    //todo 两个 map的合并
    val map1 = mutable.Map("a"->1,"b"->2,"c"->3,"d"->3)
    val map2 = mutable.Map("a"->3,"b"->2,"c"->1)
    //map1 中的所有元素，和map2中的每个kv迭代
    println(map1.foldLeft(map2)((map, kv) => {
      val i = map.getOrElse(kv._1, 0) + kv._2
      map.updated(kv._1, i)
    })
    )
  }
}

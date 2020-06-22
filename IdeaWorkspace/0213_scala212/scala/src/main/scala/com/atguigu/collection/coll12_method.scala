package com.atguigu.collection

object coll12_method {
  def main(args: Array[String]): Unit = {
//    val list = List(1,2,3,4,5,6)
    //todo 传入规则（函数）
//    println("map()："+list.map(_ * 2))
    //todo 扁平化,将整体拆分成个体,多（n）层数据，调用多（n-1）次flatten
//    val list1 = List(List(List(1,2)),List(List(3,4)))
//    println("原来的集合："+list1)
//    println("扁平化之后的："+list1.flatten.flatten)
    //todo  flatMap扁平映射：扁平化(自定义规则)+映射
//    val list2 = List(List(1,2),List(3,4))
//    println(list2.flatten.map(_ * 2))
//    println(list2.flatMap(_.map(_ * 2)))
//    val list3 = List("hello scala","hello spark")
//    println(list.flatten)
//    println(list3.flatMap(_.split(" ")))
//    val list4 = List(1,2,3,4,5,6)
//    println(list4.flatMap((i) => {
//      List(i * 2)
//    }))
//    println(list4.flatMap((i) =>  List(i * 2)))
    //todo 过滤
//    val list5 = List(1,2,3,4,5,6)
//    println(list5.filter(_ == 3))
//    val list6 = List("hello","scala","hello","spark")
//    println(list6.filter(_.startsWith("s")))
    //todo 分组
//    val list7 = List(1,2,3,4,5,6)
//    println(list7.groupBy(_ % 2))
//    val list8 = List("hello","scala","hello","spark")
//    println(list8.groupBy(word => word))
    //todo 排序
    val list9 = List(2,1,5,3,6,4)
    println(list9.sortBy(num => num))
    println(list9.sortBy(num => -num))
    println(list9.sortBy(num => num).reverse)
    println(list9.sortBy(num => num)(Ordering.Int.reverse))
    val list10 = List("2","1","5","3","6","4","11","12")
    //字符串排序可以按照字典数
    println(list10.sortBy(s => s))
    //todo 自定义规则排序
    println(list10.sortBy(_.toInt))
    val user = new User()
    user.age=20
    user.name="zs"
    val user1 = new User()
    user1.age=20
    user1.name="ls"
    //todo scala中元组自动比较大小
      //todo 先比较第一个元素，在比较第二个元素，以此类推
    val userList = List(user1,user)
    println(userList.sortBy(user => {
      (user.age,user.name)
    }))
  }
  class User{
    var age:Int=_
    var name:String=_

    override def toString: String = {
      s"User[$age，$name]"
    }
  }
}

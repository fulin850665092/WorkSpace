package com.atguigu.match1

object mat11 {
  //将该List(1,2,3,4,5,6,"test")中的Int类型的元素加一，并去掉字符串。
  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5,"list")
    println(
      list
        .filter(_.isInstanceOf[Int]) //过滤出Int类型的数据后，list的数据类型还是any
        .map(_.asInstanceOf[Int])  //将过滤后的类型转为Int
        .map(_+1)
    )
    //功能实现了，但是上面偏离了需求
    println(list.map(
      dt => {
        dt match {
          case i: Int => i + 1
          case _ => dt
        }
      }
    ))

    //使用偏函数
    var pf:PartialFunction[Any,Any]={
      case i:Int=>i+1
//      case j:String=>j
    }

    println(list.collect(pf))
      //偏函数是一个特质trait
    //map方法不支持 偏函数，只能全量数据操作，collect，只对一部分数据操作
    println(list.collect{
      case i: Int => i + 1
//      case j:String=> j //字符串不做处理
    })
  }
}

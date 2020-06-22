package com.atguigu.collection

object coll13_reduce {
  def main(args: Array[String]): Unit = {
    val list1 = List(1,2,3,4,5)
    val list2 = List(3,4,5,6)

    //参数类型，返回结果得保持一致,两两顺序运行，1，2，3，4  1+2=3 3+3=6 6+4=10
    println(list1.reduce(_+_))
    //参数前面得类型可以和后面得参数类型可以不一样,从左边开始计算
    println(list1.reduceLeft(_ + _))
    val list3 = List("a",4,5,6,7)
//    list3.reduceLeft()
    //后面得参数类型可以和前面得参数类型不一致可以不一样,从右边开始计算
      //todo  reversed.reduceLeft[B]((x, y) => op(y, x)) ->先反转，在颠倒（x，y）计算：y-x
    println(list1.reduceRight(_ + _))
    //todo    记忆方式
    //   left ：从左边加括号
    //        (((((1- 2)- 3)- 4) -5)=>-13
    //   right ：从右边加括号
    //        (1- (2 -(3 -(4 -5))))=>3
    println(list1.reduce(_-_))
    println(list1.reduceLeft(_ - _))
    println(list1.reduceRight(_ - _))
  }
}

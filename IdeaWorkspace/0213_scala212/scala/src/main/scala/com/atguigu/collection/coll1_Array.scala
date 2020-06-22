package com.atguigu.collection


object coll1_Array {
  def main(args: Array[String]): Unit = {

    //默认使用的是不可变集合：immutable
     //数组
     //构建
      val array=new Array[String](3)
      array(2)="张三"
      //操作
      //获取数组的值
      array.update(2,"lisi")
      val ints = Array(1,2,3,4)
      println(ints.mkString(","))
      val ints1 = ints :+ 4
      println(ints1.mkString(","))
      val ints2 = 0+:ints1
      println(ints2.mkString(","))
      println(array(2))
//      for (elem <- array) {
//        println(elem)
//      }
      println(array)
      val array1=new Array[Int](3)
      array1(2)=10
      //操作
      //获取数组的值
      array1.update(2,20)
      println(array1(2))
      //更新数组（地址值，不是说内容）
      val add=array1 :+ 29
      println(add eq array1)
      println(add.mkString(","))
      println(add.mkString(","))
      println(array1.mkString(","))
      //向数组的末位添加数据
      val a1=array1.+:(40)
      array1:+40
      //向数组的开始添加数据
      val a2=array1.:+(40)
      //scala 中 运算符使用：结尾，那么运算规则从右向左
//      array1 +: 40=> 40 .+:(array1)
      40+:array1
      println(a1.mkString(","))
      println(a2.mkString(","))
      //添加一个数组
      val a3 = array1++a1
      println(a3.mkString(","))
  }
}

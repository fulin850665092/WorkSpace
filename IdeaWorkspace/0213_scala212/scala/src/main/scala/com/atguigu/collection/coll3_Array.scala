package com.atguigu.collection

object coll3_Array {
  def main(args: Array[String]): Unit = {
    //多维数组
    val a1 = Array.ofDim[Int](3,3)
    a1.foreach(arr=>arr.foreach(println(_)))
    val array1 = Array(1,2,3)
    val array2 = Array(4,5,6)
    //合并数组
    val conAr = Array.concat(array1,array2)
    println(conAr.mkString(","))
    //范围数组
    val a2 = Array.range(1,10)
    println(a2.mkString(","))

    //可变数组和不可变数组的转换
      //不可变=》可变
    val buffer = array1.toBuffer
    //可变=》不可变
    val array = buffer.toArray

  }
}

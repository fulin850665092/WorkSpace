package com.atguigu.spark.core.acc

import org.apache.spark.{SparkConf, SparkContext}

object spk2_acc1 {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("acc")
    val sc : SparkContext = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1,2,3,4))
    //todo 累加器
        //累加器可以用于累加数据，但是不仅仅是数值的累加，也可以是数据的累加

    //声明累加器
    val sum = sc.longAccumulator("sum")

    rdd.foreach(
      s=>{
        sum.add(s)
        println("sum=" + sum.value)
      }
    )
    //读取累加器的值
    println("sum="+sum.value)

    sc.stop()
  }
}

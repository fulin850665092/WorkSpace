package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk18_Transfrom_sortBy {
  def main(args: Array[String]): Unit = {

    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
    //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD 转换算子 	sortBy
        //数字=》数字大小
        //字符串=》字典数
        //对象=》tuple=》按照顺序排
    val rdd = sc.makeRDD(List(1,5,3,2,4,6),3)
    //默认升序
    val rdd1 = rdd.sortBy(s=>s)
    //默认降序（false）
    val rdd2 = rdd.sortBy(s=>s,false)
    println(
      rdd1
        .collect()
        .mkString(",")
    )
    println(
      rdd2
        .collect()
        .mkString(",")
    )

    val rdd3 = sc.makeRDD(List(
      new User("zhangsan",32),
      new User("lisi",28),
      new User("lisi",19)
    ),3)
    println(rdd3.sortBy(user => (user.name, user.age),false).collect().mkString(","))


    sc.stop()
  }
  case class User(name:String,age:Int)
}

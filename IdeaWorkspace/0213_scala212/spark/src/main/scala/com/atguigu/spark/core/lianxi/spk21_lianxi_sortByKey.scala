package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.generic.Sorted

object spk21_lianxi_sortByKey{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo 	小功能：设置key为自定义类User
        //key必须实现Ordered接口
    val rdd = sc.makeRDD(List(
      (new User(),1),
      (new User(),2),
      (new User(),13)

    ))
    rdd
      .sortByKey()
      .collect()
      .foreach(println)

    sc.stop()
  }
//  case  class User(){}
case  class  User() extends  Ordered[User]{
    override def compare(that: User): Int = {
      1
    }
  }
}

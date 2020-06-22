package com.atguigu.spark.sql.lianxi

import com.atguigu.spark.sql.acc.cout_acc
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object spk1_laiinxi {
   def main(args: Array[String]): Unit = {


      val conf: SparkConf = new SparkConf().setAppName("count").setMaster("local[*]")
      val sc = new SparkContext(conf)
      val rdd: RDD[(String, Int)] = sc.makeRDD(List(("zhangsan", 20), ("lisi", 30), ("wangw", 40)))

      //创建累加器
      val acc = new cout_acc
      //注册累加器
      sc.register(acc)
      //使用累加器
      rdd.map(_._2).foreach(
         s => acc.add(s)
      )
      //获取值
      println(acc.value)

      sc.stop()
   }
}

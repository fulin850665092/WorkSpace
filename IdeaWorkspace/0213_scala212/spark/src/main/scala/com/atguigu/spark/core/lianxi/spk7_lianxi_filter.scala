package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}

object spk7_lianxi_filter {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
    //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)
    //todo 	小功能：从服务器日志数据apache.log中获取2015年5月17日的请求路径
    val rdd = sc.textFile("0213_scala212/input/apache.log")
    rdd
      .filter(s=>{
        s.split(" ")(3).substring(0,10)=="17/05/2015"
      })
      .map(s=>(s.split(" ")(3),s.split(" ")(6)))
      .collect()
      .foreach(println)

    sc.stop()
  }
}

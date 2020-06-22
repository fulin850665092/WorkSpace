package com.atguigu.spark.core.lianxi

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 计算所有分区最大值求和（分区内取最大值，分区间最大值求和）
  */
object spk5_lianxi_glom {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
    //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)

    println(
      sc
        .makeRDD(List(1,4,2,8), 2)
        .glom()   //分区数据封装成数组
        .map(_.max) //获取分区Array的最大值
        .collect()  //采集数据
        .sum        //求和
    )
    sc.stop()
  }
}

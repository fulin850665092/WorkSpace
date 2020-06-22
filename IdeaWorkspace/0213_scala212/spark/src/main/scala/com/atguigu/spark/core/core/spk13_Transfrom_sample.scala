package com.atguigu.spark.core.core

import org.apache.spark.{SparkConf, SparkContext}

object spk13_Transfrom_sample{
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    // 创建Spark上下文环境对象（连接对象），称为Driver类
      //Driver类的逻辑代码，是在Driver端执行
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD转换算子 - sample
    // 抽取数据不放回（伯努利算法）
    // 伯努利算法：又叫0、1分布。例如扔硬币，要么正面，要么反面。
    // 具体实现：根据种子和随机算法算出一个数和第二个参数设置几率比较，小于第二个参数要，大于不要
    // 第一个参数：抽取的数据是否放回，false：不放回
    // 第二个参数：抽取的几率，范围在[0,1]之间,0：全不取；1：全取；
    // 第三个参数：随机数种子

    val rdd = sc.makeRDD(1 to 10)

//    val rdd1= rdd.sample(false,0.5)

    // 第三个参数：随机数种子
    val rdd1= rdd.sample(false,0.5,10)
    val rdd2= rdd.sample(true,2,1)
    //todo
    // 抽取数据放回（泊松算法）
    // 第一个参数：抽取的数据是否放回，true：放回；false：不放回
    // 第二个参数：重复数据的几率，范围大于等于0.表示每一个元素被期望抽取到的次数
    // 第三个参数：随机数种子
//    val rdd2 = rdd.sample(true,2)

      rdd1
        .collect()
        .foreach(println)
      rdd2
        .collect()
        .foreach(println)

    sc.stop()
  }
}

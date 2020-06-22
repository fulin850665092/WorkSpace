package com.atguigu.spark.core.acc

import org.apache.spark.util.{AccumulatorV2, LongAccumulator}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object spk4_acc3 {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("acc")
    val sc: SparkContext = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List("hello spark", "spark", "hello", "hive"))

    //创建 累加器
    val acc = new ZdyAcc
    //注册累加器
    sc.register(acc)
    //使用累加器
    rdd.flatMap(_.split(" ")).foreach(
      s=>acc.add(s)
    )
    //获取累加器的值
    println(acc.value)

    sc.stop()
  }
  class  ZdyAcc extends  AccumulatorV2[String,mutable.Map[String,Int]]{

    var map: mutable.Map[String, Int] = mutable.Map[String, Int]()

    override def isZero: Boolean = map.isEmpty

    override def copy(): AccumulatorV2[String, mutable.Map[String, Int]] = new ZdyAcc

    override def reset(): Unit = map.clear()
    //累加
    override def add(v: String): Unit = {
        map.update(v,map.getOrElse(v,0)+1)
    }
    //归并所有Executor的值
    override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {

      this.map=
        this.map.foldLeft(other.value)(
                (map1,map2)=>{
                  map1.update(map2._1,map1.getOrElse(map2._1,0)+map2._2)
                  map1
                }
              )

    }

    override def value: mutable.Map[String, Int] = map
  }
}

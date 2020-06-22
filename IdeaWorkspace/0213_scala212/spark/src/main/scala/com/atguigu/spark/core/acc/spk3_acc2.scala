package com.atguigu.spark.core.acc

import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object spk3_acc2 {
  def main(args: Array[String]): Unit = {
    // 创建Spark运行配置对象
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("acc")
    val sc : SparkContext = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List("hello","spark","hello","hive"))
    //todo 自定义累加器
        //累加器可以不使用shuffle就完成数据的聚合功能
        //创建累加器
    val acc = new WordAcc()
        //注册累加器
    sc.register(acc)
        //使用累加器
    rdd.foreach(
      s=>{
        acc.add(s)
      }
    )
    //读取累加器的值
    println("acc="+acc.value)

    sc.stop()
  }
  //继承：AccumulatorV2
  // In:向累加器传递的值，Out：累加器返回的结果
  // AccumulatorV2[IN, OUT] extends Serializable
  class WordAcc extends AccumulatorV2[String,mutable.Map[String,Int]]{

     var map: mutable.Map[String, Int] = mutable.Map[String,Int]()

    //累加器是否初始化
    override def isZero: Boolean =map.isEmpty

    //复制一个累加器
    override def copy(): AccumulatorV2[String, mutable.Map[String, Int]] = new WordAcc()

    //重置累加器
    override def reset(): Unit =  map.clear()

    //累加数据
    override def add(word: String): Unit = {
      val cnt = map.getOrElse(word,0)
      map.update(word,cnt+1)
    }
    //合并累加器
    override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {
      //合并两个map
      map=this.map.foldLeft(other.value)((map,kv)=>{
            //更新map
        map(kv._1)=map.getOrElse(kv._1,0)+kv._2
        map
      })
    }
    //获取累加器的值，就是累加器的返回结果
    override def value: mutable.Map[String, Int] = map
  }
}

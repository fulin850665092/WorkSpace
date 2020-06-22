package com.atguigu.spark.core.action

import org.apache.spark.{SparkConf, SparkContext}

object spk12_action_bibao_user{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    //todo RDD -- 行动算子：闭包

    val rdd = sc.makeRDD(List(

    ),2)
    //Caused by: java.io.NotSerializableException: com.atguigu.spark.action.spk12_action_bibao_user$User
    //spark中的算子基本上都会传递匿名函数
    //而匿名函数都是有闭包的看，有可能引用外部的变量。这个时候外部的变量在执行的时候
      //会发送到Executor端，那么如果引用的变量不能序列化，就会发生错误
    //所以spark在执行前，必须检测，看看引用的变量是否能够序列化
    //todo 这个操作叫闭包检测：  ClosureCleaner.clean(f, checkSerializable)
    val user = new User()
    rdd.foreach(s=>{
//      println("=========")
      println(user)
    })
    sc.stop()
  }
  class  User()
  class  Emp extends  Serializable()
}

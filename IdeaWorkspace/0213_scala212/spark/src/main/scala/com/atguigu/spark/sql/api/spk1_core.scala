package com.atguigu.spark.sql.api

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object spk1_core {
  def main(args: Array[String]): Unit = {

    //创建环境对象
    val conf = new  SparkConf().setMaster("local[*]").setAppName("sql")
    val sparkSession = SparkSession.builder().config(conf).getOrCreate()
    //todo 导入隐式转换  这里的sparkSession其实式环境对象的名称
    //todo 要求这个对象必须使用val声明
    import sparkSession.implicits._
    val df = sparkSession.read.json("0213_scala212/input/user.json")

    df.createTempView("user")

    sparkSession.sql("select * from user").show

    //dcl
    df.select("username","age").show
    //要使用单引号'，需要倒入隐式转换
    df.select('username,'age).show

    val rdd = sparkSession.sparkContext.makeRDD(
      List(
        (1, "zhangsan", 23),
        (2, "lisi", 33),
        (3, "wangwu", 44),
      )
    )
    //RDD to DF
    val rddToDF = rdd.toDF("id","name","age")
    rddToDF.show()
    //DF to RDD
    val dfToRdd = rddToDF.rdd

    //Rdd to ds
    val userDS = rdd.map(s=>user(s._1,s._2,s._3)).toDS()
    userDS.show()
//    userDS.createTempView("user1")
    //ds to rdd
    val dsToRdd = userDS.rdd

    //df to  ds
    val dfToDs = rddToDF.as[user]
    dfToDs.show()
    //ds to df
    val dsToDf = userDS.toDF()
    dsToDf.show()

    sparkSession.stop()
  }
  case class user (var id:Int,name:String,age:Int)
}

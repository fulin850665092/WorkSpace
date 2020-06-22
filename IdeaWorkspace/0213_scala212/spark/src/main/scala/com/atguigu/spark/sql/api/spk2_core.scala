package com.atguigu.spark.sql.api

import org.apache.spark.sql.{Row, SparkSession}

object spk2_core {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[*]").appName("core").getOrCreate()
    import  spark.implicits._
    val rdd = spark.sparkContext.makeRDD(
      List(
        (1, "zhangsan", 23),
        (2, "lisi", 33),
        (3, "wangwu", 44),
      )
    )
    //RDD to DF
    val df = rdd.toDF("id","name","age")
//    df.map(row => {
//      val age  = row(2)
//      val name = row(1)
//      val id = row(0)
//      Row(id, name, age)
//    }).show
//    df.foreach(row=>{
//      println(row(0))
//    })

    val userRDD = rdd.map(s=>user(s._1,s._2,s._3))
    val ds = userRDD.toDS()
    ds.map(user=>{
      (user.id,"name:"+user.name,user.age)
    }).show()
    //自定义函数在sql中完成数据的转换操作
    df.createTempView("user")
    spark.udf.register("addName",(x:String)=>"Name:"+x)
    spark.sql("select id,addName(name),age from user ").show
    spark.udf.register("changeAge",(x:Int)=>18)
    spark.sql("select id,changeAge(age),name from user ").show
    spark.stop()
  }
  case  class  user(id:Int,name:String,age:Int)
}

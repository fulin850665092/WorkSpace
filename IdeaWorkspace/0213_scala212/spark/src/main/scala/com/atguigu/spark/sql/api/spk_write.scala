package com.atguigu.spark.sql.api

import org.apache.spark.sql.SparkSession

object spk_write {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("read").master("local[*]").getOrCreate()
    import  spark.implicits._
    val df = spark.sparkContext.makeRDD(List(
                                ("zhangsan", 11),
                                ("zhangsan13", 13),
                                ("zhangsan12", 12)
                              )).toDF("name", "id")

//    df.write.save("0213_scala212/output/write")
    //todo mode:默认是存在就报错
     //    df.write.format("json").save("0213_scala212/output/write_json")
    df.write.mode("append").format("json").save("0213_scala212/output/write_json1")
    df.write.mode("overwrite").format("json").save("0213_scala212/output/write_json")

    spark.stop()
  }
}

package com.atguigu.spark.sql.UDAF

import com.atguigu.req_sql.CityName_UDAF
import org.apache.spark.sql.SparkSession

object spk_hiveliani {
  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME", "atguigu")

    val spark = SparkSession
                                  .builder()
                                  .enableHiveSupport()
                                  .master("local[*]")
                                  .appName("hive")
                                  .config("spark.sql.warehouse.dir", "hdfs://hadoop102:8020/user/hive/warehouse")
                                  .getOrCreate()

    spark.sql("use atguigu")
    val citydata = new CityName_UDAF
    spark.udf.register("citydata",citydata)
    var df= spark.sql(
      """
        |select *
        |from (select *,rank() over (partition by area order by clickCount desc ) as  rk
        |      from (select area,product_name,count(*) as clickCount
        |            from (select a.* ,c.area,p.product_name
        |                  from user_visit_action a
        |                           join city_info c
        |                                on a.city_id=c.city_id
        |                           join product_info p
        |                                on p.product_id=a.click_product_id
        |                  where a.click_product_id > -1) t1
        |            group by area,product_name) t2) t3
        |where rk<=3
      """.stripMargin)
//    df.foreach()
    spark.sql(
      """
        |select citydata(city_name)
        |                  from user_visit_action u
        |                           join product_info p
        |                                on u.click_product_id=p.product_id
        |                           join city_info c
        |                                on u.city_id=c.city_id
        |                  where click_category_id > -1
      """.stripMargin).show()


    spark.stop()
  }
}

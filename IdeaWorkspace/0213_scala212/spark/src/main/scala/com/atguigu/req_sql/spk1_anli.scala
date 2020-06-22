package com.atguigu.req_sql

import org.apache.spark.sql.SparkSession

object spk1_anli {

  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME", "atguigu")
    val spark = SparkSession.builder().master("local[*]")
                                                  .appName("hive")
                                                  .enableHiveSupport()
                                                  .getOrCreate()

    // 创建UDAF函数
    val cityName_UDAF = new CityName_UDAF;
    //注册UDAF
    spark.udf.register("cityName_UDAF", cityName_UDAF)

    spark.sql("use atguigu")

    spark.sql(
      """
        |select u.*, c.area,c.city_name,p.product_name
        |            from user_visit_action u
        |                     join product_info p
        |                          on u.click_product_id=p.product_id
        |                     join city_info c
        |                          on u.city_id=c.city_id
        |            where click_category_id > -1
      """.stripMargin).createTempView("t1")

    spark.sql(
      """
        |    select
        |        t1.area,
        |        t1.product_name,
        |        count(*) as clickCount,
        |        cityName_UDAF(t1.city_name)
        |    from t1
        |    group by t1.area, t1.product_name
      """.stripMargin).createTempView("t2")

    spark.sql(
      """
        |select
        |   *
        |from (
        |    select
        |        *,
        |        rank() over( partition by t2.area order by t2.clickCount desc ) as rank
        |    from t2
        |) t3
        |where rank <= 3
      """.stripMargin).show()

    spark.stop()

  }
}

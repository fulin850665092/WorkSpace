package com.atguigu.spark.core.project.service

import com.atguigu.spark.core.project.bean.HotCategory
import com.atguigu.spark.core.project.common.ServiceCommon
import com.atguigu.spark.core.project.dao.HotTop10_Dao
import com.atguigu.spark.core.project.helper.{HotTop10_Accumulator, HotTop10_Accumulator1}
import com.atguigu.spark.core.project.util.AppUtil
import org.apache.spark.rdd.RDD

class HotTop10_Service extends ServiceCommon {

  private val hotTop10Dao = new HotTop10_Dao


  /**
    * todo 累加器2
    * @return
    */
  def analysis4() = {
    val rdd = hotTop10Dao.readFile("0213_scala212/input/user_visit_action.txt")

    //创建累加器
    val acc = new HotTop10_Accumulator1
    //注册累加器
    AppUtil.createSparkContext().register(acc)
    //使用累加器
    rdd.foreach(
      dt=>{
        val dts = dt.split("_")
        if (dts(6) != "-1"){
          acc.add((dts(6),"click"))
        }
        else if (dts(8)!= "null"){
          val ids = dts(8).split(",")
          ids.foreach(
            s=>acc.add((s,"order"))
          )
        }
        else if (dts(10)!= "null"){
          val ids = dts(10).split(",")
          ids.foreach(
           s=> acc.add((s,"pay"))
          )
        }
      }
    )
    //返回值
    val result = acc.value
    result
      .map(_._2)
      .toList
      .sortWith(
        (left,right)=>{
          if (left.orderCount>right.clickCount){
            true
          }else  if (left.orderCount==right.clickCount){
            if (left.orderCount>right.orderCount){
              true
            }
            else if (left.orderCount==right.orderCount){
              left.payCount>right.payCount
            }
            else{
              false
            }
          }
          else{
            false
          }
        }
      )
      .take(10)
  }

  /**
    * todo 累加器
    * @return
    */
  def analysis3() = {
    val rdd = hotTop10Dao.readFile("0213_scala212/input/user_visit_action.txt")

    //创建累加器
    val acc = new HotTop10_Accumulator
    //注册累加器
    AppUtil.createSparkContext().register(acc)
    //使用累加器
    rdd.foreach(
      dt=>acc.add(dt)
    )
    //返回值
    val result = acc.value
    result
      .map(_._2)
      .toList
      .sortWith(
        (left,right)=>{
          if (left.orderCount>right.clickCount){
            true
          }else  if (left.orderCount==right.clickCount){
                if (left.orderCount>right.orderCount){
                  true
                }
                else if (left.orderCount==right.orderCount){
                  left.payCount>right.payCount
                }
                else{
                  false
                }
          }
          else{
            false
          }
        }
      )
      .take(10)
  }

  /**
    * todo 封装成对象
    * @return
    */
  def analysis2() = {
      val dataRDD: RDD[String] = hotTop10Dao.readFile("0213_scala212/input/user_visit_action.txt")
      val categoryRDD = dataRDD.flatMap(
        data => {
          val datas = data.split("_")
          if (datas(6) != "-1") {
            // 点击的场合
            List((datas(6), HotCategory(datas(6), 1, 0, 0)))
          } else if (datas(8) != "null") {
            // 下单的场合
            val ids = datas(8).split(",")
            ids.map(
              id => {
                (id, HotCategory(id, 0, 1, 0))
              }
            )
          } else if (datas(10) != "null") {
            // 支付的场合
            val ids = datas(10).split(",")
            ids.map(
              id => {
                (id, HotCategory(id, 0, 0, 1))
              }
            )
          } else {
            Nil
          }
        }
      )

      val reduceRDD: RDD[HotCategory] = categoryRDD.reduceByKey(
        (c1, c2) => {
          c1.clickCount = c1.clickCount + c2.clickCount
          c1.orderCount = c1.clickCount + c2.orderCount
          c1.payCount = c1.payCount + c2.payCount
          c1
        }
      ).map(_._2)

      reduceRDD.sortBy(
        data => {
          (data.clickCount, data.orderCount, data.payCount)
        },
        false
      ).take(10)
  }

  /**
    * todo 拼接2
    * @return
    */
  def analysis1() = {
    val rdd = hotTop10Dao.readFile("0213_scala212/input/user_visit_action.txt")
    rdd.flatMap(
      s=>{
        val dt = s.split("_")
        if(dt(6) != "-1")   List((dt(6),(1,0,0)))                                //19
        else if(dt(8) != "null") dt(8).split(",").map((_,(0,1,0)))       //2,6,3,14
        else if(dt(10) != "null") dt(10).split(",").map((_,(0,0,1)))      //15,1,20
        else Nil
      })
      .reduceByKey(
        (x, y) => {
          (x._1 + y._1, x._2 + y._2, x._3 + y._3) })
      .sortBy(_._2, false)
      .take(10)      //行动算子

  }

  /**
    * todo 拼接
    * @return
    */
  override def analysis() = {
    val rdd = hotTop10Dao.readFile("0213_scala212/input/user_visit_action.txt")
    // 缓存rdd，重复使用
    val rddCache = rdd.cache()
    // 获取点击品类
    val result =
      rddCache
        .map(s => (s.split("_")(6), 1))
        .filter(_._1 != "-1")
        .reduceByKey(_ + _)
    //      result.collect().foreach(println)

    //  品类下单统计
    val result1 =
      rddCache
        .map(s => (s.split("_")(8)))
        .filter(_ != "null")
        .flatMap(_.split(",").map((_, 1)))
        .reduceByKey(_ + _)
    //    result1.collect().foreach(println)

    // 品类支付统计
    val result2 = rddCache
      .map(s => (s.split("_")(10)))
      .filter(_ != "null")
      .flatMap(_.split(",").map((_, 1)))
      .reduceByKey(_ + _)
    //    result3.collect().foreach(println)

    // 转换
    // (category, count) => (category, clickcount, ordercount, paycount)
    //点击
    val clickRdd = result.map {
      case (category, count) => {
        (category, (count, 0, 0))
      }
    }
    //下单
    val orderRdd = result1.map {
      case (category, count) => {
        (category, (0, count, 0))
      }
    }
    //支付
    val payRdd = result2.map {
      case (category, count) => {
        (category, (0, 0, count))
      }
    }
    // 连接
    val unionRdd = clickRdd.union(orderRdd).union(payRdd)

    //聚合
    unionRdd.reduceByKey(
      (x, y) => {
        (x._1 + y._1, x._2 + y._2, x._3 + y._3)
      }
    ).sortBy(_._2,false).take(10)
  }

}

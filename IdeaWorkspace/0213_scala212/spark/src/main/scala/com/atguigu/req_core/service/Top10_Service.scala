package com.atguigu.req_core.service

import com.atguigu.req_core.acc.{Top10_acc, Top10_acc2, Top10_acc3}
import com.atguigu.req_core.application.Top10_Application.connect
import com.atguigu.req_core.bean.HotCategory
import com.atguigu.req_core.dao.{Top10_Dao, WcDao}
import com.atguigu.summer.framework.core.TService
import com.atguigu.summer.framework.util.AppUtil
import org.apache.spark.rdd.RDD
//todo 一次性统计每个品类点击的次数，下单的次数和支付的次数：（品类，（点击总数，下单总数，支付总数））
class Top10_Service extends  TService{

  private val top10_Dao = new Top10_Dao

  /**
    * todo flatMap封装
    * @return
    */
  override def analysis() = {

      val rdd = top10_Dao.readFile("0213_scala212/input/user_visit_action.txt")

      rdd
        .flatMap(
            dt=>{
                // line
              val dts = dt.split("_")
              if (dts(6) != "-1") List((dts(6),(1,0,0)))                                  //过滤点击
              else if (dts(8) != "null") dts(8).split(",").map(s=>(s,(0,1,0)))     //过滤下单
              else if (dts(10) != "null") dts(10).split(",").map(s=>(s,(0,0,1)))   //过滤支付
              else Nil
            })
        .reduceByKey((x,y)=>(x._1+y._1,x._2+y._2,x._3+y._3))
        .sortBy(_._2,false)
        .take(10)
  }
  /**
    * todo bean封装
    * @return
    */
   def analysisByBean() = {

    val rdd = top10_Dao.readFile("0213_scala212/input/user_visit_action.txt")

    rdd
      .flatMap(
        dt=>{
          // line
          val dts = dt.split("_")
          if (dts(6) != "-1") List(( dts(6),HotCategory(dts(6),1,0,0)))                           //过滤点击
          else if (dts(8) != "null") dts(8).split(",").map(s=>(s,HotCategory(s,0,1,0)))    //过滤下单
          else if (dts(10) != "null") dts(10).split(",").map(s=>(s,HotCategory(s,0,0,1)))  //过滤支付
          else Nil
        })
      .reduceByKey(
         (x,y) => {
           (   x.clickCount += y.clickCount,
               x.orderCount += y.orderCount,
               x.payCount   += y.payCount
           )
           x
         }
        )
       .map(_._2)
       .sortBy(s=>(s.clickCount,s.orderCount,s.payCount),false)
       .take(10)
  }
  /**
    * todo  累加器
    * @return
    */
  def analysisByAcc() = {

    val rdd = top10_Dao.readFile("0213_scala212/input/user_visit_action.txt")
    //创建累加器器
    val top10_acc = new Top10_acc
    //注册
    AppUtil.SparkContext().register(top10_acc)
    //使用
    rdd
      .foreach(
        dt=>{
          // line
          val dts = dt.split("_")
          if (dts(6) != "-1") top10_acc.add("clik",dts(6))                                              //过滤点击
          else if (dts(8) != "null") dts(8).split(",").foreach( s=>top10_acc.add(("order",s)))   //过滤下单
          else if (dts(10) != "null")  dts(10).split(",").foreach(s=>top10_acc.add(("pay",s)))   //过滤支付
          else Nil
        })

    //获取 top10 的值
    top10_acc
      .value
      .map(_._2)
      .toList
      .sortWith(
      (ct1,ct2)=>{
        if (ct1.clickCount>ct2.clickCount){
          true
        }
        else if(ct1.clickCount==ct2.clickCount){
              if(ct1.orderCount>ct2.orderCount){
                true
              }
              else if(ct1.orderCount==ct2.orderCount){
                ct1.payCount>ct2.payCount
              }
              else {
                false
              }
        }else{
          false
        }
      })
     .take(10)

  }
  /**
    * todo  累加器2
    * @return
    */
  def analysisByAcc2() = {

    val rdd = top10_Dao.readFile("0213_scala212/input/user_visit_action.txt")
    //创建累加器器
    val top10_acc = new Top10_acc2
    //注册
    AppUtil.SparkContext().register(top10_acc)
    //使用
    rdd
      .foreach(
        dt=>{
          // line
          val dts = dt.split("_")
          if (dts(6) != "-1")         top10_acc.add(dts(6))       //过滤点击
          else if (dts(8) != "null")  top10_acc.add(dts(8))       //过滤下单
          else if (dts(10) != "null") top10_acc.add(dts(10))      //过滤支付
          else Nil
        })

    //获取 top10 的值
    top10_acc
      .value
      .map(_._2)
      .toList
      .sortWith(
        (ct1,ct2)=>{
          if (ct1.clickCount>ct2.clickCount){
            true
          }
          else if(ct1.clickCount==ct2.clickCount){
            if(ct1.orderCount>ct2.orderCount){
              true
            }
            else if(ct1.orderCount==ct2.orderCount){
              ct1.payCount>ct2.payCount
            }
            else {
              false
            }
          }else{
            false
          }
        })
      .take(10)

  }
  /**
    * todo  累加器,老师写的就是猛
    * @return
    */
  def analysisByAccTeacher() = {

    val rdd = top10_Dao.readFile("0213_scala212/input/user_visit_action.txt")
    //创建累加器器
    val top10_acc = new Top10_acc3
    //注册
    AppUtil.SparkContext().register(top10_acc)
    //使用
    rdd
      .foreach(
        dt=>{
          // line
          val dts = dt.split("_")
          if (dts(6) != "-1") top10_acc.add("clik",dts(6))                                              //过滤点击
          else if (dts(8) != "null") dts(8).split(",").foreach( s=>top10_acc.add(("order",s)))   //过滤下单
          else if (dts(10) != "null")  dts(10).split(",").foreach(s=>top10_acc.add(("pay",s)))   //过滤支付
          else Nil
        })

    //获取 top10 的值
    top10_acc
      .value
      .map(_._2)
      .toList
      .sortWith(
        (ct1,ct2)=>{
          if (ct1.clickCount>ct2.clickCount){
            true
          }
          else if(ct1.clickCount==ct2.clickCount){
            if(ct1.orderCount>ct2.orderCount){
              true
            }
            else if(ct1.orderCount==ct2.orderCount){
              ct1.payCount>ct2.payCount
            }
            else {
              false
            }
          }else{
            false
          }
        })
      .take(10)

  }

  /**
    * todo  map封装
    *
    * @return
    */
  def analysisByJoin() = {
    val rdd = top10_Dao.readFile("0213_scala212/input/user_visit_action.txt")

    //点击数
    val cilckCount = rdd
      .map(s => (s.split("_")(6), 1))
      .filter(_._1 != "-1")
      .reduceByKey(_ + _)


    //订单数
    val orderCount = rdd
      .flatMap(s => s.split("_")(8).split(",").map((_, 1)))
      .filter(_._1 != "null")
      .reduceByKey(_ + _)


    //支付数
    val payCount = rdd
      .flatMap(s => s.split("_")(10).split(",").map((_, 1)))
      .filter(_._1 != "null")
      .reduceByKey(_ + _)


    //join，聚合，排序
    cilckCount
      .join(orderCount)
      .join(payCount)
      .mapValues(s=>(s._1._1,s._1._2,s._2))
      .sortBy(_._2,false)

  }



}

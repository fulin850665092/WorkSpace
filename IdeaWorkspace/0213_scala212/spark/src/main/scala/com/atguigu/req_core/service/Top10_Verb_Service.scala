package com.atguigu.req_core.service

import java.util.Calendar

import com.atguigu.req_core.acc.Top10_acc
import com.atguigu.req_core.bean.{HotCategory, UserVisitAction}
import com.atguigu.req_core.dao.{Top10_Dao, Top10_Verb_Dao}
import com.atguigu.req_core.util.TimeUtil
import com.atguigu.summer.framework.core.TService
import com.atguigu.summer.framework.util.AppUtil

//todo 一次性统计每个品类点击的次数，下单的次数和支付的次数：（品类，（点击总数，下单总数，支付总数））
class Top10_Verb_Service extends  TService{

  private val top10_Verb_Dao = new Top10_Verb_Dao

  /**
    * todo 所有页面的跳转率
    * @return
    */
  override def analysis() = {

    val rdd = top10_Verb_Dao.readFile("0213_scala212/input/user_visit_action.txt")

    val cacheRdd =
      rdd.map(
          s => {
            val dts = s.split("_")
            UserVisitAction(
              dts(0),
              dts(1).toLong,
              dts(2),
              dts(3).toLong,
              dts(4),
              dts(5),
              dts(6).toLong,
              dts(7).toLong,
              dts(8),
              dts(9),
              dts(10),
              dts(11),
              dts(12).toLong
            )
          }).cache()
    //统计所有页面次数（分母）
    val pageCountMap = cacheRdd
                                      .map(s => (s.page_id, 1))
                                      .reduceByKey(_ + _)
                                      .collect()
                                      .toMap
    val pageCounts = cacheRdd
                                        .groupBy(_.session_id)
                                        .mapValues(
                                          iter => {
                                            val ids = iter
                                                                  .toList
                                                                  .sortWith(_.action_time < _.action_time)
                                                                  .map(_.page_id)

                                            val idsTuples = ids
                                                                            .zip(ids.tail)
                                                                            .map(s => (s._1 + "_" + s._2, 1))
                                            idsTuples
                                          }
                                        ).map(_._2).flatMap(s => s).reduceByKey(_ + _)
    //求转换率
    pageCounts.foreach(
        s=>{
          val fenzi = s._2
          val fenmu = pageCountMap(s._1.split("_")(0).toLong)
          println("跳转页面："+s._1+"，转换率："+fenzi.toDouble / fenmu)
        }
    )

  }
  /**
    * todo 所有页面的跳转率
    * @return
    */
   def analysisByPages() = {

     val rdd = top10_Verb_Dao.readFile("0213_scala212/input/user_visit_action.txt")
     //缓存
     val cacheRdd = rdd.cache()

     //对象封装
     val userRdd = cacheRdd
       .map(
         s => {
           val dts = s.split("_")
           UserVisitAction(
             dts(0),
             dts(1).toLong,
             dts(2),
             dts(3).toLong,
             dts(4),
             dts(5),
             dts(6).toLong,
             dts(7).toLong,
             dts(8),
             dts(9),
             dts(10),
             dts(11),
             dts(12).toLong
           )
         }
       )
     //业务需要统计页面
     val pages = List(1,2,3,4,5,6,7)
     //业务需要统计跳转页面 (1-2)
     val pagesZip = pages.zip(pages.tail)
     //所有统计页面的跳转次数
     val pagesCountMap = userRdd
                                         .filter(s=>pages.contains(s.page_id))
                                         .map(s => (s.page_id, 1))
                                         .reduceByKey(_ + _)
                                         .collect()
                                         .toMap

     //所有统计页面的连续跳转页面统计
     val pageCounts = userRdd
                                         .groupBy(_.session_id)
                                         .mapValues(
                                           iter => {
                                               val ids = iter
                                                                     .toList
                                                                     .sortWith(_.action_time < _.action_time)
                                                                     .map(_.page_id)
                                               val idstTuples = ids
                                                                                 .zip(ids.tail)
                                                                                 .filter(s => pagesZip.contains(s))
                                                                                 .map(s => (s._1 + "_" + s._2, 1))
                                               idstTuples
                                           })
                                         .flatMap(_._2)
                                         .reduceByKey(_ + _)

     //求跳转换率
     pageCounts.foreach(
         s=>{
             val fenzi = s._2                                                 //分子
             val fenmu = pagesCountMap(s._1.split("_")(0).toLong)     //分母
             println("跳转页面："+s._1+"，转换率："+fenzi.toDouble / fenmu)
          }
     )

   }

  /**
    * todo 所有页面的跳转率
    * @return
    */
  def analysisByPagesTime() = {

    val rdd = top10_Verb_Dao.readFile("0213_scala212/input/user_visit_action.txt")
    //缓存
    val cacheRdd = rdd.cache()

    //对象封装
    val userRdd = cacheRdd
      .map(
        s => {
          val dts = s.split("_")
          UserVisitAction(
            dts(0),
            dts(1).toLong,
            dts(2),
            dts(3).toLong,
            dts(4),
            dts(5),
            dts(6).toLong,
            dts(7).toLong,
            dts(8),
            dts(9),
            dts(10),
            dts(11),
            dts(12).toLong
          )
        }
      )


    //所有统计页面的连续跳转页面统计
    userRdd
      .groupBy(_.session_id)
      .mapValues(
        iter => {
          val ids = iter
            .toList
            .sortWith(_.action_time < _.action_time)
            .map(s=>(s.page_id,s.action_time))
          val idstTuples = List((ids.head,ids.size))
            .zip(List(ids.last))
            .map(s => (s._1._1._1 + "_" +s._2._1,TimeUtil.calculatetimeGapSecond(s._1._1._2,s._2._2)/s._1._2))
          idstTuples
        })
      .flatMap(_._2)
      .collect()

  }
}

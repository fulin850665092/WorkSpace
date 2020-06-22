package com.atguigu.req_core.service

import com.atguigu.req_core.bean.UserVisitAction
import com.atguigu.req_core.dao.{Top10_Time_Dao, Top10_Verb_Dao}
import com.atguigu.req_core.util.TimeUtil
import com.atguigu.summer.framework.core.TService

//todo 一次性统计每个品类点击的次数，下单的次数和支付的次数：（品类，（点击总数，下单总数，支付总数））
class Top10_Time_Service extends  TService{

  private val top10_Time_Dao = new Top10_Time_Dao


  /**
    * todo 所有页面的跳转率
    * @return
    */
  def analysis() = {

    val rdd = top10_Time_Dao.readFile("0213_scala212/input/user_visit_action.txt")
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
      .groupByKey()
      .mapValues(iter=>iter.sum/iter.size)
      .collect()

  }
}

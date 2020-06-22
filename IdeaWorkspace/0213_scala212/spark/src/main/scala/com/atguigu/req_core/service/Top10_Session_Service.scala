package com.atguigu.req_core.service

import com.atguigu.req_core.acc.Top10_acc
import com.atguigu.req_core.bean.{HotCategory, UserVisitAction}
import com.atguigu.req_core.dao.{Top10_Dao, Top10_Session_Dao}
import com.atguigu.summer.framework.core.TService
import com.atguigu.summer.framework.util.AppUtil

//todo Top10热门品类中每个品类的Top10活跃Session统计
class Top10_Session_Service extends  TService{

  private val top10_Session_Dao = new Top10_Session_Dao

  /**
    * todo 简单的封装
    * @return
    */
  def analysisByTop10(accBeans: List[HotCategory]) = {

    val rdd = top10_Session_Dao.readFile("0213_scala212/input/user_visit_action.txt")
    //广播变量提升性能
    val broadList = AppUtil.SparkContext().broadcast(accBeans.map(_.id))
    val userRdd = rdd.map(
      dt => {
        val dts = dt.split("_")
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
    userRdd
      .filter(s=>s.click_category_id!="-1"&&broadList.value.contains(s.click_category_id.toString))
      .map(s=>(s.click_category_id+"_"+s.session_id,1))
      .reduceByKey(_+_)
      .map(s=>(s._1.split("_")(0),(s._1.split("_")(1),s._2)))
      .groupByKey()
      .mapValues(_.toList.sortWith(_._2>_._2).take(10))
  }

  override def analysis() = {

  }

}

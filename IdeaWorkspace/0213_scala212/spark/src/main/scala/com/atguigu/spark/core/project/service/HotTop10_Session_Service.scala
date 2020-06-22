package com.atguigu.spark.core.project.service

import com.atguigu.spark.core.project.bean.{HotCategory, UserVisitAction}
import com.atguigu.spark.core.project.common.ServiceCommon
import com.atguigu.spark.core.project.dao.{HotTop10_Dao, HotTop10_Session_Dao}
import com.atguigu.spark.core.project.util.AppUtil

import scala.util.control.Breaks


class HotTop10_Session_Service extends ServiceCommon {

  private val tp10_Session_Dao = new HotTop10_Session_Dao



  def sessionAnalysis(categories: List[HotCategory]) = {

      val rdd = tp10_Session_Dao.readFile("0213_scala212/input/user_visit_action.txt")
      val userRdd = rdd.map(
        line => {
          val dt = line.split("_")
          UserVisitAction(
            dt(0),
            dt(1).toLong,
            dt(2),
            dt(3).toLong,
            dt(4),
            dt(5),
            dt(6).toLong,
            dt(7).toLong,
            dt(8),
            dt(9),
            dt(10),
            dt(11),
            dt(12).toLong
          )
        }
      )
    val filterRdd = userRdd.filter(
      user => {
        //保留点击行为
        if (user.click_category_id !="-1") {
          var flag = false
          Breaks.breakable {
            for (category <- categories) {
              //判断点击的品类id是否在Top10 的热门品类中
              if (category.id.toLong == user.click_category_id) {
                flag = true
                Breaks.break()
              }
            }
          }
          flag
        }
        else {
          false
        }
      }
    )

      filterRdd
        .map( user => (user.click_category_id + "_" + user.session_id, 1))  //4_71f1c966-11e4-450f-81d2-c0b334710ccc,1
        .reduceByKey(_+_)                                                    //4_71f1c966-11e4-450f-81d2-c0b334710ccc,10
        .map(s=> (s._1.split("_")(0),(s._1.split("_")(1),s._2)))  //4,(71f1c966-11e4-450f-81d2-c0b334710ccc,10)
  //      .map {
  //            case (k, sum) => {
  //              (k.split("_")(0),(k.split("_")(1),sum))
  //            }
  //        }
        .groupByKey()                                       //4,List((71f1c966-11e4-450f-81d2-c0b334710ccc,10),....)
        .mapValues(_.toList.sortWith(  _._2>_._2).take(10))

  }
  def sessionAnalysis1(categories: List[HotCategory]) = {

    val rdd = tp10_Session_Dao.readFile("0213_scala212/input/user_visit_action.txt")
    val userRdd = rdd.map(
      line => {
        val dt = line.split("_")
        UserVisitAction(
          dt(0),
          dt(1).toLong,
          dt(2),
          dt(3).toLong,
          dt(4),
          dt(5),
          dt(6).toLong,
          dt(7).toLong,
          dt(8),
          dt(9),
          dt(10),
          dt(11),
          dt(12).toLong
        )
      }
    )
    //减少数据的序列化
    val ids = categories.map(_.id)
    //todo 使用广播变量提升性能
    val idsBroadcast = AppUtil.createSparkContext().broadcast(ids)

    val filterRdd = userRdd.filter(
      //保留点击行为并且判断点击的品类id是否在Top10 的热门品类中
      user =>  user.click_category_id != "-1" && idsBroadcast.value.contains(user.click_category_id.toString) //todo id是String，click_category_id是Long
    )

    filterRdd
      .map( user => (user.click_category_id + "_" + user.session_id, 1))  //4_71f1c966-11e4-450f-81d2-c0b334710ccc,1
      .reduceByKey(_+_)                                                    //4_71f1c966-11e4-450f-81d2-c0b334710ccc,10
      .map(s=> (s._1.split("_")(0),(s._1.split("_")(1),s._2)))  //4,(71f1c966-11e4-450f-81d2-c0b334710ccc,10)
      .groupByKey()                                       //4,List((71f1c966-11e4-450f-81d2-c0b334710ccc,10),....)
      .mapValues(_.toList.sortWith(  _._2>_._2).take(10))

  }

  override def analysis(): Any = {

  }
}

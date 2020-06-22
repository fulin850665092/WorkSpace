package com.atguigu.spark.core.project.service

import com.atguigu.spark.core.project.bean.{HotCategory, UserVisitAction}
import com.atguigu.spark.core.project.common.ServiceCommon
import com.atguigu.spark.core.project.dao.HotTop10_PageFlow_Dao

class HotTop10_PageFlow_Service extends ServiceCommon {

  private val hotTop10_PageFlow_Dao = new HotTop10_PageFlow_Dao

  override def analysis() = {
    val rdd = hotTop10_PageFlow_Dao.readFile("0213_scala212/input/user_visit_action.txt")
    //封装样例类
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
    val cacheRdd = userRdd.cache()
    //todo 分母
    val pageCountMap = cacheRdd
                                      .map(s => (s.page_id, 1))
                                      .reduceByKey(_ + _)
                                      .collect()
                                      .toMap
//    println(pageCountMap)
    //todo 分子
    val idToSumRdd = cacheRdd
                                      .groupBy(_.session_id)                    //将数据分组按照session分组
                                      .mapValues(                               //对value的操作用mapValues
                                            iter => {
                                              val ids = iter
                                                                    .toList                        //迭代器不可遍历转为list
                                                                    .sortWith((left, right) => left.action_time < right.action_time)    //排序（正序）
                                                                    .map(_.page_id)                 // 获取页面id （1，2，3）

                                              val tupIds = ids
                                                                            .zip(ids.tail) // 拉链 id （1，2，3）  idtail(2,3)->(1-2,2-3)
                                                                            .map(s => (s._1 + "-" + s._2, 1))
                                              tupIds                          //返回 （1-2，1）
                                            }                                  // （session，（1-2，1））
                                        )
                                      .map(_._2)                //不需要session
                                      .flatMap(s => s)          //取每个元素
                                      .reduceByKey(_ + _)       //分组聚合




    //todo 计算单跳转率
    idToSumRdd.foreach {
      case (pageIds,count) => {
        val ids = pageIds.split("-")
        val pageCoun= pageCountMap(ids(0).toLong)      //查找分母
        println(pageIds + "的转换率为：" + count.toDouble/pageCoun)
      }
    }



  }
}

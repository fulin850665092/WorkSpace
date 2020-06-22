package com.atguigu.req_streaming.service

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.logging.SimpleFormatter

import com.atguigu.req_streaming.bean.Ads_log
import com.atguigu.req_streaming.dao.ClickCountDao
import com.atguigu.req_streaming.util.JdbcUtil
import com.atguigu.summer.framework.core.TService

/**
  * 实时统计每天各地区各城市各广告的点击总流量，并将其存入MySQL
  */
class ClickCountService extends TService{

  private val clickCountDao = new ClickCountDao

  override def analysis()= {
    val kfkDS = clickCountDao.readKafka

    //将数据转为样例类
    val ads_logDs =
                                      kfkDS.map(dt => {
                                          val dts = dt.split(" ")
                                          Ads_log(
                                            dts(0),
                                            dts(1),
                                            dts(2),
                                            dts(3),
                                            dts(4),
                                          )
                                      })
    val sdf = new SimpleDateFormat("yyyy-MM-dd")
    val reduceDS =
                                                ads_logDs.map(
                                                  dt => {
                                                    ((sdf.format(new Date(dt.dt.toLong)),dt.area, dt.city, dt.adid), 1)
                                                   })
                                                .reduceByKey(_ + _)

    reduceDS.foreachRDD(
      rdd=>{
        rdd.foreachPartition {
          iter => {
            val conn = JdbcUtil.getConnection
            var pstat=conn.prepareStatement(
              """
                |insert into area_city_ad_count (dt,area,city,adid,count)
                |values (?,?,?,?,?)
                |on duplicate key
                |update count = count+?
              """.stripMargin)
            iter.foreach {
              case ((dt,area, city, adid), count) => {
                pstat.setString(1, dt)
                pstat.setString(2, area)
                pstat.setString(3, city)
                pstat.setString(4, adid)
                pstat.setLong(5, count)
                pstat.setLong(6, count)
                pstat.executeUpdate()
              }
            }
            pstat.close()
            conn.close()
          }
        }
      }
    )
  }
}

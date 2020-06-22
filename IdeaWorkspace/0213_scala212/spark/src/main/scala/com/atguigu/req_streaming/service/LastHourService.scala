package com.atguigu.req_streaming.service

import com.atguigu.req_streaming.bean.Ads_log
import com.atguigu.req_streaming.dao.LastHourDao
import com.atguigu.summer.framework.core.TService
import org.apache.spark.streaming.{Minutes, Seconds}

class LastHourService extends TService{

  private val lastHourDao = new LastHourDao

  override def analysis()= {

    val kfkDS = lastHourDao.readKafka()

    //将数据转为样例类
    val ads_logDS = kfkDS.map(dt => {
                          val dts = dt.split(" ")
                          Ads_log(
                            dts(0),
                            dts(1),
                            dts(2),
                            dts(3),
                            dts(4),
                          )
                        })

    val tsDs =
                                ads_logDS
                                  .map(
                                    dt => {
                                      val ts = dt.dt.toLong
                                      ((dt.adid, ts / 10000 * 10000), 1)
                                    }
                                  )
    var groupDS=
                                    tsDs
                                      .reduceByKeyAndWindow((x:Int,y:Int)=>x+y,Minutes(1),Seconds(10))
                                      .map{
                                        case ((adid,ts),count)=>{
                                         (adid,(ts,count))
                                        }}
                                      .groupByKey()
   var resultDS=
                                     groupDS
                                       .mapValues(
                                            iter=>{
                                              iter.toList.sortWith(_._1>_._2)
                                            })
    resultDS.print()
  }
}

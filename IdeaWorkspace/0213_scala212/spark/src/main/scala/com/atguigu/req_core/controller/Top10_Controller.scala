package com.atguigu.req_core.controller

import com.atguigu.req_core.service.{Top10_Service, WcService}
import com.atguigu.summer.framework.core.TController

class Top10_Controller extends TController{
  private val top10_Service = new Top10_Service

  override def executor() = {
//    val rdd = top10_Service.analysis
//    rdd.foreach(println)
//    val rdd1 = top10_Service.analysisByBean
//    rdd1.foreach(println)
//    val rdd2 = top10_Service.analysisByJoin
//    rdd2.foreach(println)
//    val rdd3 = top10_Service.analysisByAcc
//    rdd3.foreach(println)
//    val rdd4 = top10_Service.analysisByAcc
//    rdd4.foreach(println)
      val rdd5 = top10_Service.analysisByAccTeacher()
      rdd5.foreach(println)

  }
}

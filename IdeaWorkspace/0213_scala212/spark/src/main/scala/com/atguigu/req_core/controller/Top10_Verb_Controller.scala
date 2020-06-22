package com.atguigu.req_core.controller

import com.atguigu.req_core.service.{Top10_Service, Top10_Verb_Service}
import com.atguigu.summer.framework.core.TController

class Top10_Verb_Controller extends TController{
  private val top10_Verb_Service = new Top10_Verb_Service

  override def executor() = {

//    val rdd = top10_Verb_Service.analysis
//    rdd.foreach(println)
//    val rdd1 = top10_Verb_Service.analysisByPages()
//    rdd1.foreach(println)
        val rdd1 = top10_Verb_Service.analysisByPagesTime()

        rdd1.foreach(println)
  }
}

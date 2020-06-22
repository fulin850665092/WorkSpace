package com.atguigu.req_core.controller

import com.atguigu.req_core.application.Top10_Application.connect
import com.atguigu.req_core.service.{Top10_Service, Top10_Session_Service}
import com.atguigu.summer.framework.core.TController

class Top10_Session_Controller extends TController{
  private val top10_Session_Service = new Top10_Session_Service
  private val top10_Service = new Top10_Service

  override def executor() = {

    val accBeans = top10_Service.analysisByAcc
    val rdd = top10_Session_Service.analysisByTop10(accBeans)

    rdd.collect().foreach(println)

  }
}

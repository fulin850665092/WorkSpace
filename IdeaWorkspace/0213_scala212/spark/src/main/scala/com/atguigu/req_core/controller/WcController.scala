package com.atguigu.req_core.controller

import com.atguigu.req_core.service.WcService
import com.atguigu.summer.framework.core.TController

class WcController extends TController{
  private val wcService = new WcService

  override def executor() = {
    val rdd = wcService.analysis()
    rdd.collect().foreach(println)
  }
}

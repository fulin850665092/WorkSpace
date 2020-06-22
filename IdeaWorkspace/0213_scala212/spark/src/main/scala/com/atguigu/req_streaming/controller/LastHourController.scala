package com.atguigu.req_streaming.controller

import com.atguigu.req_streaming.service.LastHourService
import com.atguigu.summer.framework.core.TController

class LastHourController extends TController{
    private val lastHourService = new LastHourService

  override def executor() = {
    lastHourService.analysis()
  }
}

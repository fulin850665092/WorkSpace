package com.atguigu.req_streaming.controller

import com.atguigu.req_streaming.service.ClickCountService
import com.atguigu.summer.framework.core.TController

class ClickCountController extends TController{

  private val clickCountService = new ClickCountService

  override def executor() = {
    clickCountService.analysis()
  }
}

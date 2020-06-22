package com.atguigu.req_streaming.application

import com.atguigu.req_streaming.controller.ClickCountController
import com.atguigu.summer.framework.TApplication

object ClickCountApp extends  TApplication with App {

  private val clickCountController = new ClickCountController

  start("sparkStreaming")(){
    clickCountController.executor()
  }

}

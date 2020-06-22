package com.atguigu.req_streaming.application

import com.atguigu.req_streaming.controller.MockDataController
import com.atguigu.summer.framework.TApplication

object MockDataApp extends TApplication with App {

  private val controller = new MockDataController

  start("sparkStreaming")(){
    controller.executor()
  }

}

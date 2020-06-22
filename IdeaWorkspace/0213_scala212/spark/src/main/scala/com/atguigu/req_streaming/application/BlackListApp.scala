package com.atguigu.req_streaming.application

import com.atguigu.req_streaming.application.MockDataApp.{controller, start}
import com.atguigu.req_streaming.controller.BlackListController
import com.atguigu.summer.framework.TApplication

object BlackListApp extends TApplication with App{

  private val blackListController = new BlackListController

  start("sparkStreaming")(){
    blackListController.executor()
  }

}

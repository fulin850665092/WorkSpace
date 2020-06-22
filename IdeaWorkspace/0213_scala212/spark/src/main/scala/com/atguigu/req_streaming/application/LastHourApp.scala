package com.atguigu.req_streaming.application

import com.atguigu.req_streaming.controller.LastHourController
import com.atguigu.summer.framework.TApplication

object LastHourApp extends TApplication with App {
    private val hourController = new LastHourController
    start("sparkStreaming")(){
        hourController.executor
    }

}

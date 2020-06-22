package com.atguigu.req_core.application

import com.atguigu.req_core.controller.WcController
import com.atguigu.summer.framework.TApplication

object WcApplication extends TApplication with  App {

  start("spark")(appName = "WcApp"){

    val wcController = new WcController
    wcController.executor()

  }
}

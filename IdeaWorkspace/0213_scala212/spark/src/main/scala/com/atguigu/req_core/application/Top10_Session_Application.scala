package com.atguigu.req_core.application

import com.atguigu.req_core.controller.{Top10_Controller, Top10_Session_Controller}
import com.atguigu.summer.framework.TApplication

object Top10_Session_Application extends TApplication with  App {

  start("spark")(appName = "Top10_Session_Controller"){

    val top10_Session_Controller = new Top10_Session_Controller
    top10_Session_Controller.executor()

  }
}

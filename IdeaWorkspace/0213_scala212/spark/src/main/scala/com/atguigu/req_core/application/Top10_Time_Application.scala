package com.atguigu.req_core.application

import com.atguigu.req_core.controller.{Top10_TIme_Controller, Top10_Verb_Controller}
import com.atguigu.summer.framework.TApplication

object Top10_Time_Application extends TApplication with  App {

  start("spark")(appName = "Top10"){

    val top10_TIme_Controller = new Top10_TIme_Controller

    top10_TIme_Controller.executor()

  }



}

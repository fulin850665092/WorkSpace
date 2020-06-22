package com.atguigu.req_core.controller

import com.atguigu.req_core.service.{Top10_Time_Service, Top10_Verb_Service}
import com.atguigu.summer.framework.core.TController

class Top10_TIme_Controller extends TController{
  private val top10_Time_Service = new Top10_Time_Service

  override def executor() = {

        val rdd1 = top10_Time_Service.analysis()

        rdd1.foreach(
          s=>{
            println("连续页面：" + s._1 + "，平均时间：" + s._2)
          }
        )
  }
}

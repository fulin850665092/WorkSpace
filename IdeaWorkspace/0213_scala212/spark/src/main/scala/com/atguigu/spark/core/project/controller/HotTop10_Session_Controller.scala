package com.atguigu.spark.core.project.controller

import com.atguigu.spark.core.project.common.ControllerCommon
import com.atguigu.spark.core.project.service.{HotTop10_Service, HotTop10_Session_Service}

class HotTop10_Session_Controller extends ControllerCommon{

  private val top10_Session_Service = new HotTop10_Session_Service
  private val top10_Service = new HotTop10_Service

  override def execute(): Unit = {

    val categories = top10_Service.analysis4()

//    val resultRdd = top10_Session_Service.sessionAnalysis(categories)
    val resultRdd = top10_Session_Service.sessionAnalysis1(categories)

    resultRdd.collect().foreach(println)
  }
}

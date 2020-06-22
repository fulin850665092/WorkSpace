package com.atguigu.spark.core.project.controller

import com.atguigu.spark.core.project.common.ControllerCommon
import com.atguigu.spark.core.project.service.{HotTop10_PageFlow_Service, HotTop10_PageFlow_verb_Service}

class HotTop10_PageFlow_Controller extends ControllerCommon{

  private val hotTop10_PageFlow_Service = new HotTop10_PageFlow_Service
  private val hotTop10_PageFlow_verb_Service = new HotTop10_PageFlow_verb_Service
  override def execute(): Unit = {

    val resultRdd = hotTop10_PageFlow_Service.analysis()
//    resultRdd.collect().foreach(println)
  }
   def executeVerb() ={

    val resultRdd = hotTop10_PageFlow_verb_Service.analysis()
    //    resultRdd.collect().foreach(println)
  }
}

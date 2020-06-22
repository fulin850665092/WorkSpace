package com.atguigu.spark.core.project.controller

import com.atguigu.spark.core.project.common.ControllerCommon
import com.atguigu.spark.core.project.service.WordCountService

class WordCountController  extends  ControllerCommon {

  private val wordCountService = new WordCountService()

  def  execute()={
    val rdd = wordCountService.analysis()
    println(rdd.collect.mkString(","))
  }

}

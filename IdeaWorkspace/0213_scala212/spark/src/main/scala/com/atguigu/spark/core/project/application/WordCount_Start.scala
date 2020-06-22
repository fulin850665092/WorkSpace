package com.atguigu.spark.core.project.application

import com.atguigu.spark.core.project.common.AppCommon
import com.atguigu.spark.core.project.controller.WordCountController

object WordCount_Start extends App with AppCommon{

  start(appName = "WordCount"){
    val controller = new WordCountController()
    controller.execute()
  }


}

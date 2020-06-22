package com.atguigu.spark.core.project.application

import com.atguigu.spark.core.project.common.AppCommon
import com.atguigu.spark.core.project.controller.{HotTop10_PageFlow_Controller, HotTop10_Session_Controller}

object HotTop10_PageFlow_Start extends App with AppCommon{

    start(appName = "HotTop10App"){
        val hotTop10_PageFlow_Controller = new HotTop10_PageFlow_Controller()
        hotTop10_PageFlow_Controller.execute()
        println("==========================")
        hotTop10_PageFlow_Controller.executeVerb()
    }
}

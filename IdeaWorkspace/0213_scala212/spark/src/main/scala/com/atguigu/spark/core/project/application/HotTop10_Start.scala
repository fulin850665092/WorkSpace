package com.atguigu.spark.core.project.application

import com.atguigu.spark.core.project.common.AppCommon
import com.atguigu.spark.core.project.controller.HotTop10_Controller

object HotTop10_Start extends App with AppCommon{
    start(appName = "HotTop10App"){
        val hotTop10Controller = new HotTop10_Controller()
        hotTop10Controller.execute()
    }
}

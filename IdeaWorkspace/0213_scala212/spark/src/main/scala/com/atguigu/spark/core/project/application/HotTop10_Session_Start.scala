package com.atguigu.spark.core.project.application

import com.atguigu.spark.core.project.common.AppCommon
import com.atguigu.spark.core.project.controller.{HotTop10_Controller, HotTop10_Session_Controller}

object HotTop10_Session_Start extends App with AppCommon{

    start(appName = "HotTop10App"){
        val hotTop10_Session_Controller = new HotTop10_Session_Controller()
        hotTop10_Session_Controller.execute()
    }
}

package com.atguigu.spark.core.project.controller

import com.atguigu.spark.core.project.common.ControllerCommon
import com.atguigu.spark.core.project.service.HotTop10_Service

class HotTop10_Controller extends ControllerCommon{

  private val service = new HotTop10_Service
  override def execute(): Unit = {
//     val resultRdd = service.analysis()  //拼接
     val resultRdd = service.analysis2()  //拼接2
//     val resultRdd = service.analysis2()      //封装对象
//     val resultRdd = service.analysis3()      //累加器
//     val resultRdd = service.analysis4()      //累加器2
     resultRdd.foreach(println)
  }
}

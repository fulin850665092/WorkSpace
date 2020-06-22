package com.atguigu.spark.core.project.common

import com.atguigu.spark.core.project.util.AppUtil
import org.apache.spark.{SparkConf, SparkContext}

trait AppCommon {

  def  start(master:String="local[*]",appName:String="application")( op: => Unit)={

    //todo 创建SparkContext对象（sc）
    AppUtil.createSparkContext(master,appName)

    //todo 逻辑
    try {
      op
    } catch {
      case ex :Exception  => ex.printStackTrace
    }

    //todo 关闭连接对象
    AppUtil.stop()

  }
}

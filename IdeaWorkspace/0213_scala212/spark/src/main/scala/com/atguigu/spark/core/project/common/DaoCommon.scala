package com.atguigu.spark.core.project.common

import com.atguigu.spark.core.project.util.AppUtil

trait DaoCommon {

  def  readFile(path:String)={
      AppUtil.createSparkContext().textFile(path)
  }

}

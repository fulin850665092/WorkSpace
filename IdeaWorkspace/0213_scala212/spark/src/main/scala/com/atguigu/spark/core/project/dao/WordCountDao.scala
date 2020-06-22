package com.atguigu.spark.core.project.dao

import com.atguigu.spark.core.project.common.DaoCommon
import com.atguigu.spark.core.project.util.AppUtil
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

class WordCountDao extends DaoCommon{

  val sc = AppUtil.createSparkContext()

  def readDao()= {
    // 读取文件数据
    sc.textFile("0213_scala212/input/word.txt")
  }
}

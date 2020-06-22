package com.atguigu.spark.core.project.util

import org.apache.spark.{SparkConf, SparkContext}

/**
  * todo 线程工具类：获取Sc对象
  *
  */
object AppUtil {
  //todo
    private val threadLocal = new ThreadLocal[SparkContext]

  /**
    * 创建sc对象
    * @param master
    * @param appName
    * @return
    */
    def createSparkContext(master:String="local[*]",appName:String="application")={

      var sc = threadLocal.get()
      if (sc==null) {
        // 创建Spark运行配置对象
        val sparkConf = new SparkConf().setMaster(master).setAppName(appName)
        sc= new SparkContext(sparkConf)
        threadLocal.set(sc)
      }
      sc
    }

  /**
    * 关闭sc对象，移除threadLocal对象
    */
  def stop(): Unit ={
      var sc = threadLocal.get()
      if (sc!=null){
        sc.stop()
      }
      threadLocal.remove()
    }
}

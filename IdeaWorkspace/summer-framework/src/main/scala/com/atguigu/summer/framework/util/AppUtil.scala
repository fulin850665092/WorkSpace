package com.atguigu.summer.framework.util

import org.apache.spark.streaming.{Duration, Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}



/**
  * todo 线程工具类：获取Sc对象
  */
object AppUtil {
    //todo 创建ThreadLocal 对象
    private val threadLocal = new ThreadLocal[SparkContext]
    private val sscLocal = new ThreadLocal[StreamingContext]
  /**
    * 创建ssc对象
    * @param master
    * @param appName
    * @return
    */
  def StreamingContext(master:String="local[*]",appName:String="application",time:Duration=Seconds(5))={

    var ssc = sscLocal.get()
    if (ssc==null) {
      // 创建Spark运行配置对象
      val sparkConf = new SparkConf().setMaster(master).setAppName(appName)
      ssc= new StreamingContext(sparkConf,time)
      ssc.sparkContext.textFile("0213_scala212/input/banner1.txt").foreach(println)
      Thread.sleep(2000)
      sscLocal.set(ssc)
    }
    ssc
  }
    /**
    * 创建sc对象
    * @param master
    * @param appName
    * @return
    */
    def SparkContext(master:String="local[*]",appName:String="application")={

      var sc = threadLocal.get()
      if (sc==null) {
        // 创建Spark运行配置对象
        val sparkConf = new SparkConf().setMaster(master).setAppName(appName)
        sc= new SparkContext(sparkConf)
        sc.textFile("0213_scala212/input/banner1.txt").foreach(println)
        Thread.sleep(2000)
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

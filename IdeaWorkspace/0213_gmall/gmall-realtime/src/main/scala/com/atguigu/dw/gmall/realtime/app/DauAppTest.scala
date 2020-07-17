package com.atguigu.dw.gmall.realtime.app

import com.alibaba.fastjson.JSON
import com.atguigu.dw.gmall.common.constant.GmallConstant
import com.atguigu.dw.gmall.realtime.StartupLog
import com.atguigu.dw.gmall.realtime.util.{MyKafkaUtil, RedisUtil}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import redis.clients.jedis.Jedis

object DauAppTest {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("DauApp")
    val ssc = new StreamingContext(conf, Seconds(5))
    val sourceStream =
      MyKafkaUtil.getKafkaStream(ssc, GmallConstant.TOPIC_STARTUP)
//      sourceStream.print(100)
    // 1. 调整数据结构
    val starupLogDSteam = sourceStream.map {
      log=> JSON.parseObject(log, classOf[StartupLog])
    }
    //保存到redis
    starupLogDSteam.foreachRDD(
      rdd=>{
        rdd.foreachPartition(
          iter=>{
            val client = RedisUtil.getJedisClient
            iter.foreach(
              json=>{
                println(json)
                client.sadd("dau:"+json.logDate,json.uid)
              }
            )
            client.close()
          }
        )
      }
    )
    ssc.start()
    ssc.awaitTermination()
  }
}

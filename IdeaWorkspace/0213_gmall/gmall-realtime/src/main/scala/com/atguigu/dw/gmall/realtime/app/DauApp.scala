package com.atguigu.dw.gmall.realtime.app

import java.text.SimpleDateFormat
import java.util.Date

import com.alibaba.fastjson.JSON
import com.atguigu.dw.gmall.common.constant.GmallConstant
import com.atguigu.dw.gmall.realtime.StartupLog
import com.atguigu.dw.gmall.realtime.util.{MyKafkaUtil, RedisUtil}
import org.apache.hadoop.conf.Configuration
import org.apache.spark.{SparkConf, rdd}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.util.parsing.json.JSONObject

object DauApp {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("DauApp").setMaster("local[2]")
    val ssc = new StreamingContext(conf,Seconds(5))

    //获取kafka流
    val kafkaDstream = MyKafkaUtil.getKafkaStream(ssc,GmallConstant.TOPIC_STARTUP)

    //增加字段，作为key,当天yyyy-MM-dd作为key
    val startLog = kafkaDstream.map(
      json => {
        val startupLog = JSON.parseObject(json, classOf[StartupLog])
        val date = new Date()
        //当前登陆时间
        startupLog.logDate = new SimpleDateFormat("yyyy-MM-dd").format(date)
        //当前登陆的小时
        startupLog.logHour = new SimpleDateFormat("HH").format(date)
        startupLog
      })
    //redis 数据过滤（跨批次）
    val filterUids = startLog.transform(
      rdd => {
        //每个批次获取一次连接
        val jedisClient = RedisUtil.getJedisClient
        val uidSet = jedisClient.smembers(GmallConstant.TOPIC_STARTUP + ":" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        //使用广播变量广播到Executor，先加载redis中的数据，避免每条数据都访问redis造成连接问题
        val uidBroadcast = ssc.sparkContext.broadcast(uidSet)
        jedisClient.close()
        //过滤没有重复的
        rdd.filter( log => !uidBroadcast.value.contains(log.uid))
      }
    )
    //批次内数，据数据去重
    val filterGroupUids = filterUids
      .map(log => (log.uid, log))
      .groupByKey
      .flatMap {
        case (_, logIt) => logIt.toList.sortBy(_.ts).take(1)  //取第一次启动的数据
      }

    //把数据写入redis中
    filterGroupUids.foreachRDD(
      rdd=>{
        rdd.foreachPartition(
          iter=>{
            val client = RedisUtil.getJedisClient
            iter.foreach(
              log=>{
                client.sadd(GmallConstant.TOPIC_STARTUP+":"+log.logDate,log.uid)
              }
            )
            client.close()
          }
        )
      }
    )
    //将数据写入phoenix
    filterGroupUids.foreachRDD(rdd => {
      rdd.foreach(log => {
        println(log.logType)
      })
      //引入phoenix
      import org.apache.phoenix.spark._
//      rdd.saveToPhoenix(
//        "GMALL_DAU",
//        Seq("MID", "UID", "APPID", "AREA", "OS", "CHANNEL", "LOGTYPE", "VERSION", "TS", "LOGDATE", "LOGHOUR"),
//        ,new Configuration, //hadoop的包
//        Some("hadoop202,hadoop203,hadoop204:2181"))
      // 参数1: 表名  参数2: 列名组成的 seq 参数 zkUrl: zookeeper 地址
      rdd.saveToPhoenix(
        "GMALL_DAU",
        Seq("MID", "UID", "APPID", "AREA", "OS", "CHANNEL", "LOGTYPE", "VERSION", "TS", "LOGDATE", "LOGHOUR"),
        zkUrl = Some("hadoop202,hadoop203,hadoop204:2181"))
    })


    ssc.start()
    ssc.awaitTermination()
  }
}

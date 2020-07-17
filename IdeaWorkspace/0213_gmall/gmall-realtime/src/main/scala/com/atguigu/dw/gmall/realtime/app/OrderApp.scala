package com.atguigu.dw.gmall.realtime.app

import java.text.SimpleDateFormat
import java.util.Date

import com.alibaba.fastjson.{JSON, JSONObject}
import com.atguigu.dw.gmall.common.constant.GmallConstant
import com.atguigu.dw.gmall.realtime.OrderInfo
import com.atguigu.dw.gmall.realtime.util.MyKafkaUtil
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object OrderApp extends BaseApp {

  override def run(ssc: StreamingContext): Unit = {

      val kfkDS = MyKafkaUtil.getKafkaStream(ssc,GmallConstant.GMALL_ORDER_INFO)
      val orderInfoDS = kfkDS.map(
        json => {
          val orderInfo = JSON.parseObject(json, classOf[OrderInfo])
          //数据脱敏
          orderInfo.consignee_tel=orderInfo.consignee_tel.substring(0,3)+ "****" + orderInfo.consignee_tel.substring(7, 11)
          orderInfo.consignee=orderInfo.consignee.substring(0,1)+"**"
          val date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderInfo.create_time)
          orderInfo.create_date=new SimpleDateFormat("yyyy-MM-dd").format(date)
          orderInfo.create_hour=new SimpleDateFormat("HH").format(date)
          orderInfo
        }
      )
      import  org.apache.phoenix.spark._
      orderInfoDS.foreachRDD(
        rdd =>{
          rdd.saveToPhoenix(
            "GMALL_ORDER_INFO",
            Seq("ID", "PROVINCE_ID", "CONSIGNEE", "ORDER_COMMENT", "CONSIGNEE_TEL", "ORDER_STATUS", "PAYMENT_WAY", "USER_ID", "IMG_URL", "TOTAL_AMOUNT", "EXPIRE_TIME", "DELIVERY_ADDRESS", "CREATE_TIME", "OPERATE_TIME", "TRACKING_NO", "PARENT_ORDER_ID", "OUT_TRADE_NO", "TRADE_BODY", "CREATE_DATE", "CREATE_HOUR"),
            zkUrl = Some("hadoop202,hadoop203,hadoop204:2181")
          )}
      )
}
}
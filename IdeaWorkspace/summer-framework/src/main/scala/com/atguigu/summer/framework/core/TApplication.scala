package com.atguigu.summer.framework

import java.net.{ServerSocket, Socket}

import com.atguigu.summer.framework.util.{AppUtil, PropertiesUtil}
import org.apache.spark.streaming.{Duration, Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext, streaming}


trait TApplication extends App {
    var  connect:Any= null
    /**
      * 启动应用
      * 1. 函数柯里化
      * 2. 控制抽象
      */
    def start( t:String = "jdbc")(master:String="local[*]",appName:String="app")( op : =>Unit ): Unit = {
//    def start( t:String = "jdbc")(master:String="local[*]",appName:String="app")( op : =>Unit )(implicit time:Duration=Seconds(5)): Unit = {
        // TODO 1. 初始化环境
        println(t + "环境初始化。。。")
        t match {
            case "server"=>  connect= new ServerSocket(PropertiesUtil.getValue("server.port").toInt)
            case "server1"=> connect= new ServerSocket(PropertiesUtil.getValue("server.port1").toInt)
            case "server2"=> connect= new ServerSocket(PropertiesUtil.getValue("server.port2").toInt)
            case "client"=>  connect= new Socket(PropertiesUtil.getValue("server.host"),PropertiesUtil.getValue("server.port").toInt)
            case "client1"=> connect= new Socket(PropertiesUtil.getValue("server.host"),PropertiesUtil.getValue("server.port1").toInt)
            case "client2"=> connect= new Socket(PropertiesUtil.getValue("server.host"),PropertiesUtil.getValue("server.port2").toInt)
            case "spark"=>   connect= AppUtil.SparkContext(master,appName)
            case "sparkStreaming"=>   connect= AppUtil.StreamingContext()
            case "jdbc"=>    connect=null
        }

        // TODO 2. 业务逻辑
        try {
            op
        } catch {
            case ex: Exception => println("业务执行失败 ：" + ex.getMessage)
        }

        // TODO 3. 环境关闭
        println(t + "环境关闭。。。")
        if (t=="server"||t=="server1"||t=="server2"){
            val server=connect.asInstanceOf[ServerSocket]
            if(!server.isClosed) server.close()
        }else if(t=="client"){
            val client=connect.asInstanceOf[Socket]
            if(!client.isClosed) client.close()
        }else if(t=="spark"){
            val sc = connect.asInstanceOf[SparkContext]
            sc.stop()
        }else if(t=="sparkStreaming"){
            val sc = connect.asInstanceOf[StreamingContext]
            sc.start()
            sc.awaitTermination()
        }

    }
}

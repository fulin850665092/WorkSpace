package com.atguigu.spark.streaming

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.util.control.NonFatal

//todo 自定义Receiver
object SparkStreaming06_DIY {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("Streaming")
    val ssc = new  StreamingContext(conf,Seconds(3))
    //自定义采集器
    val fileDS = ssc.receiverStream(new MyReciver("localhost",9999))

    fileDS.flatMap(_.split(" "))
                      .map((_,1))
                      .reduceByKey(_+_)
                      .print()
    //启动采集器
    ssc.start()
    //等待采集器停止（阻塞）
    ssc.awaitTermination()

  }

  class MyReciver( host: String,port: Int) extends  Receiver[String](StorageLevel.MEMORY_ONLY){
    private var socket: Socket = _

    override def onStart(): Unit = {
      socket = new Socket(host, port)
      new Thread("Socket Receiver") {
        setDaemon(true)
        override def run() { receive() }
      }.start()

    }

    /** Create a socket connection and receive data until receiver is stopped */
    def receive() {
        val reader = new BufferedReader(new InputStreamReader(socket.getInputStream, "utf-8"))
        var str: String = null
        while (true) {
          if ((str = reader.readLine()) != null) store(str)
        }
    }

    override def onStop(): Unit = {
      if (socket != null) {
        socket.close()
        socket = null
      }
    }


  }
}

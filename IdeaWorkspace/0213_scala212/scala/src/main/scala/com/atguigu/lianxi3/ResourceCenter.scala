package com.atguigu.lianxi3

import java.io.ObjectInputStream
import java.net.{ServerSocket, Socket}

object ResourceCenter {

    def main(args: Array[String]): Unit = {

        // TODO 1. 创建服务，接收资源请求
        val receiver = new ServerSocket(PropertiesUtil.getValue("driver_resourceCenter.port").toInt)
        println("资源中心已经启动。。。")

        while ( true ) {
            val driverRef: Socket = receiver.accept()
            new Thread(
                new Runnable {
                    override def run(): Unit = {
                        // TODO 接收Driver传递的数据
                        val driverRefIn = new ObjectInputStream(driverRef.getInputStream)
                        val message: Message = driverRefIn.readObject().asInstanceOf[Message]
                        println(message)
                        val count = PropertiesUtil.getValue("executor.count").toInt
                        //生成几个执行器
                        for ( i <- 1 to count) {
                            val executor = new Executor(i, PropertiesUtil.getValue("driver_executor.host"),PropertiesUtil.getValue("driver_executor.port").toInt )
                            executor.startup()
                        }
                    }
                }
            ).start()
        }
    }
}

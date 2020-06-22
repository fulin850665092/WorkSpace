package com.atguigu.lianxi3

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.{ServerSocket, Socket}

import com.atguigu.lianxi2.Client.{connect, start}
import com.atguigu.lianxi2.Task1

import scala.io.Source

object Driver {

    def main(args: Array[String]): Unit = {
    //todo ResourceCenter和Driver通信

        // TODO 连接资源中信息，创建并且创建Executor
        val resourceCenterRef = new Socket(PropertiesUtil.getValue("driver_resourceCenter.host"), PropertiesUtil.getValue("driver_resourceCenter.port").toInt)
        println("资源中心已经连接，数据准备中")
        val resourceCenterRefOut = new ObjectOutputStream(resourceCenterRef.getOutputStream)
        resourceCenterRefOut.writeObject(
            Message(s"资源已连接"))
        resourceCenterRefOut.flush()
        // 释放和资源中心的连接
        resourceCenterRef.close
    //todo ResourceCenter和Executor通信
        val receiver = new ServerSocket(PropertiesUtil.getValue("driver_executor.port").toInt )
        // TODO 统计Executor端的数据（新建单独线程，统计结果）
        // 线程安全问题
        //新建一个线程轮询所有的执行器，如果全部返回结果则统计
        //向Array中填充Executor数量的-1，如果接收到数据则覆盖-1，直到没有-1，则说明拿到所有结果
        val results: Array[Int] = Array.fill(PropertiesUtil.getValue("executor.count").toInt)(-1)
        //var sum = 0;
        new Thread(
            new Runnable {
                override def run(): Unit = {
                    // TODO 统计结果的线程
                    var flg = true
                    while (flg) {
                        //判断Array中是否有-1
                        if ( results.contains(-1) ) {
                            Thread.sleep(100)
                        } else {
                            // 所有的线程都已经计算完毕
                             //val end = System.currentTimeMillis()
                            println("计算完毕，结果为 " + results.sum )
                            flg = false
                            //执行完成，直接退出
                            System.exit(0)
                        }
                    }
                }
            }
        ).start()

        // TODO 给Executor端的发送任务，并且接收计算结果
        while ( true ) {
            val executorRef: Socket = receiver.accept()
//            println("执行器已经连接上了。。。")
            new Thread(
                new Runnable {
                    override def run(): Unit = {

                        // TODO  向Executor发送计算任务
                        val executorRefOut =
                            new ObjectOutputStream(executorRef.getOutputStream)
                        val task = new Task3()
                        task.logic = _*2
                        executorRefOut.writeObject(task)
                        executorRefOut.flush()
                        executorRef.shutdownOutput()

                        // TODO 获取Executor端计算结果
                        val executorRefIn =
                            new ObjectInputStream(executorRef.getInputStream)
                        val m: Message = executorRefIn.readObject().asInstanceOf[Message]
                        val datas: Array[String] = m.content.split("=|&")
                        // executorId=${id}&result=$i
                        // executorId
                        // id
                        // result
                        // i
                        results(datas(1).toInt-1) = datas(3).toInt
                        println("获取计算结果 = " + datas(3).toInt)
                    }
                }
            ).start()
        }

    }
}

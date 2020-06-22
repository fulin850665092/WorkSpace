package com.atguigu.lianxi3

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.Socket

import com.atguigu.lianxi.Task
import com.atguigu.lianxi2.Task1

import scala.io.Source

case class Executor( id:Int, driverHost:String, driverPort:Int ) {

    /**
      * 启动执行器
      */
    def startup() ={
        println(s"执行器【$id】开始启动")
        println(s"执行器【$id】开始链接驱动器")
        val driverRef = new Socket(driverHost, driverPort)
        var result1=List[String]()
        // TODO 接收Driver端发送的计算任务的数据
        val driverRefIn = new ObjectInputStream(driverRef.getInputStream)
        val task: Task3 = driverRefIn.readObject().asInstanceOf[Task3]
        driverRef.shutdownInput()
        // TODO 执行计算
        val i: Int = task.logic(id)
        // TODO 将计算结果返回给Driver端
        val driverRefOut = new ObjectOutputStream(driverRef.getOutputStream)
        driverRefOut.writeObject(Message(s"executorId=${id}&result=$i"))
        driverRefOut.flush()
        //关闭
        if (!driverRef.isClosed) {
            driverRef.close
        }
    }
}

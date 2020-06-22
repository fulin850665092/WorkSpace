package com.atguigu.lianxi2

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.ServerSocket

import com.atguigu.lianxi.Task
import com.atguigu.summer.framework.TApplication

object Server2 extends TApplication{

    start("server2")(){
      while (true) {
        val server = connect.asInstanceOf[ServerSocket]
        while (true) {
          var client = server.accept()
          new Thread(
            new Runnable {
              override def run(): Unit = {
                //接收
                val ois = new ObjectInputStream(client.getInputStream)
                val task = ois.readObject().asInstanceOf[Task1]
                client.shutdownInput()
                //输出
                val oos = new ObjectOutputStream(client.getOutputStream)
                val result = task.count1()
                oos.writeObject(result)
                oos.flush()
                client.shutdownOutput()
                if (!client.isClosed) {
                  client.close()
                }
                client = null
              }
            }
          ).start()
        }
      }
    }

}

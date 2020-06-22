package com.atguigu.scala

import java.io.ObjectInputStream
import java.net.ServerSocket

object server2 {
  def main(args: Array[String]): Unit = {
    //连接
    val server = new ServerSocket(9999)
    val acc = server.accept()
    while (true) {
      //接收
      val ois = new ObjectInputStream(acc.getInputStream)
      val fun = ois.readObject().asInstanceOf[Bean]
      val res=fun.f(fun.num,fun.num1,_+_)
      println(res)
      //发送结果
      val os = acc.getOutputStream
      os.write(res)
      //关闭
      os.close()
      ois.close()
    }
    server.close()
  }
}

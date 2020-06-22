package com.atguigu.scala

import java.io.ObjectInputStream
import java.net.ServerSocket

object server {

  def main(args: Array[String]): Unit = {
    val server = new ServerSocket(9999)
    while (true) {
      val acc = server.accept()
      val ois = new ObjectInputStream(acc.getInputStream)
      val fun = ois.readObject().asInstanceOf[(Int,Int,(Int,Int)=>Int) => Int]
      println(fun(2, 3,_+_))
      val result = fun(2, 3,_+_)
      val os = acc.getOutputStream
      os.write(result);
      os.close()
      ois.close()
    }
    server.close()
  }
}

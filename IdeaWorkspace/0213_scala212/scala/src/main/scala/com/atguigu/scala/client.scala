package com.atguigu.scala

import java.io.ObjectOutputStream
import java.net.Socket

import com.atguigu.scala

object client {

  def main(args: Array[String]): Unit = {

    val client = new Socket("localhost", 9999);
    val oos = new ObjectOutputStream(client.getOutputStream)

    def sum(i: Int, j: Int, f: (Int, Int) => Int) = {
      f(i, j)
    }

    oos.writeObject(sum _)
    val is = client.getInputStream;
    val result = is.read()
    println(result)
    is.close()
    oos.close()
    client.close()
  }
}

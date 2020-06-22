package com.atguigu.lianxi

import java.io.{ObjectInputStream, ObjectOutputStream}
import java.net.Socket

import com.atguigu.summer.framework.TApplication

object Client3 extends TApplication{

    start("client")(){
      val client=connect.asInstanceOf[Socket]
      //输出
      val oos=new ObjectOutputStream(client.getOutputStream)
      val task=new Task()
      task.num=10
      task.num1=20
      task.logic=_+_
      oos.writeObject(task)
      client.shutdownOutput()

      //接收
      val ois=new ObjectInputStream(client.getInputStream)
      val result=ois.readObject().asInstanceOf[Int]
      println(result)
      client.shutdownInput()

    }



}

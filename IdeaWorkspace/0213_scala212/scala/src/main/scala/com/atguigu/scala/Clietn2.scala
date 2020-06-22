package com.atguigu.scala

import java.io.ObjectOutputStream
import java.net.Socket

object Clietn2  {

  def main(args: Array[String]): Unit = {
    val client= new Socket("localhost",9999);
    //发送
    val oos=new ObjectOutputStream(client.getOutputStream)
    val bean=new Bean
    bean.num=48
    bean.num1=19
    def sum(i:Int,j:Int,f:(Int,Int)=>Int):Int={
      f(bean.num,bean.num1)
    }
    bean.f=sum _
    oos.writeObject(bean)
    oos.flush()
    //接收返回值
    val is=client.getInputStream;
    val result=is.read()
    println(result)
    //关闭
    is.close()
    oos.close()
    client.close()
  }
}

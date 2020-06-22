package com.atguigu.lianxi2

import java.io
import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}
import java.net.Socket

import com.atguigu.lianxi.Task
import com.atguigu.summer.framework.TApplication

import scala.collection.mutable.ListBuffer
import scala.io.Source

object Client extends TApplication{

    var result1=List[String]()
    start("client1")(){
      val t1 =new Thread(
        new Runnable {
          override def run(): Unit = {
            val client=connect.asInstanceOf[Socket]
            val source = Source.fromFile("0213_scala212/input/word.txt").getLines()
            val listDat = source.toList
            //输出
            val oos=new ObjectOutputStream(client.getOutputStream)
            val task=new Task1()
            task.list=listDat
            task.logic=(list:List[String])=>{
              list
                .flatMap(_.split(" "))
                .groupBy(s => s)
                .map(s => (s._1, s._2.size))
                .toList
                .sortBy(_._2)(Ordering.Int.reverse)
                .map(s=>s._1+":"+s._2)
            }
            oos.writeObject(task)
            client.shutdownOutput()

            //接收
            val ois=new ObjectInputStream(client.getInputStream)
            val result=ois.readObject().asInstanceOf[List[String]]
            println("server1："+result)
            result1=result
            client.shutdownInput()
          }
        }
      ).start()
    }
    var result2=List[String]()
    start("client2")(){
      val t2 =new Thread(
        new Runnable {
          override def run(): Unit = {
            val client=connect.asInstanceOf[Socket]
            val source = Source.fromFile("0213_scala212/input/test.txt").getLines()
            val listDat = source.toList
            val buffer = ListBuffer[(String,String)]()
            for (elem <- listDat) {
              val str1 = elem.replaceAll("\"","")
//              buffer.append(str)
              val str = str1.split(",")
              buffer.append((str(0), str(1)))
              buffer.toList
            }
            //输出
            val oos=new ObjectOutputStream(client.getOutputStream)
            val task=new Task1()
            task.list1=buffer.toList
            task.logic1=(list:List[(String,String)])=>{
              list.flatMap(x=> x._1.split(" ").map((_,x._2)))
                .groupBy(_._1)
                .mapValues(_.map(_._2.trim.toInt).sum)
                .toList
                .sortBy(_._2)(Ordering.Int.reverse)
                .map(s=>s._1+":"+s._2)
            }
            oos.writeObject(task)
            client.shutdownOutput()

            //接收
            val ois=new ObjectInputStream(client.getInputStream)
            val result=ois.readObject().asInstanceOf[List[String]]
            println("server2："+result)
            result2=result
            client.shutdownInput()
          }
        }
      ).start()
    }
    //主线程等待
    while (result1 == Nil||result2== Nil){
      Thread.sleep(2000);
    }
    val listCount = List(result1,result2)
    println("client：" +
      listCount
        .flatMap(s=>s)
        .groupBy(s=>s.split(":")(0))
        .mapValues(_.map(_.split(":")(1).toInt).sum)
        .toList
        .sortBy(_._2)(Ordering.Int.reverse)
        .map(s=>s._1+":"+s._2)
    )


}

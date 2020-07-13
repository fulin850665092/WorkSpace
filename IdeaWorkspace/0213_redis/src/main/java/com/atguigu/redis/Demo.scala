package com.atguigu.redis

import java.util

import redis.clients.jedis.Jedis

import scala.collection.mutable
/**
  * 连接单机
  */
object Demo {

  def main(args: Array[String]): Unit = {
    val client = new Jedis("hadoop202",8000)
//    client.set("k4","v4")
//    client.lpush("list","1,2,3,4")
//    val list = client.lrange("list",0,-1)
//    list.forEach(println)
//    client.sadd("set","1,2,3,4,5")
//    val set = client.smembers("set")
//    set.forEach(println)
//    client.hset("k1","name","sz")
    val map= new util.HashMap[String,String]()
    map.put("name","ls")
    map.put("age","20")
    client.hmset("k2",map)
    client.close()



  }
}

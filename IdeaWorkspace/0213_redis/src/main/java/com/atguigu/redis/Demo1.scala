package com.atguigu.redis

import java.util

import redis.clients.jedis.{HostAndPort, Jedis, JedisCluster}

/**
  * 连接集群
  */
object Demo1{

  def main(args: Array[String]): Unit = {
    val cluster = new JedisCluster(new HostAndPort("hadoop202",6379))
//    cluster.set("k4","v4")
//    cluster.lpush("list","1,2,3,4")
//    val list = cluster.lrange("list",0,-1)
//    list.forEach(println)
//    cluster.sadd("set","1,2,3,4,5")
//    val set = cluster.smembers("set")
//    set.forEach(println)
//    cluster.hset("k1","name","sz")
    val map= new util.HashMap[String,String]()
    map.put("name","ls")
    map.put("age","20")
    cluster.hmset("k2",map)
    cluster.close()



  }
}

package com.atguigu.req_core.service

import com.atguigu.req_core.dao.WcDao
import com.atguigu.summer.framework.core.TService
import org.apache.spark.rdd.RDD

class WcService extends  TService{

  private val wcdao = new WcDao

  override def analysis() = {

      val rdd = wcdao.readFile("0213_scala212/input/word.txt")
      val wordRDD: RDD[String] = rdd.flatMap( _.split(" ") )
      val word2OneRDD: RDD[(String, Int)] = wordRDD.map((_,1))
      val word2GroupRDD = word2OneRDD.groupBy(_._1)
      val word2CountRDD = word2GroupRDD.map(s=>(s._1,s._2.size))
      word2CountRDD
  }


}

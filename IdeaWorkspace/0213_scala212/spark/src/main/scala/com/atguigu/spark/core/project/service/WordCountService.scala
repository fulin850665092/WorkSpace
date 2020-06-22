package com.atguigu.spark.core.project.service

import com.atguigu.spark.core.project.common.ServiceCommon
import com.atguigu.spark.core.project.dao.WordCountDao
import org.apache.spark.rdd.RDD

class WordCountService extends ServiceCommon{

    private val wordCountDao = new WordCountDao()

    override def analysis()={
        val rdd = wordCountDao.readFile("0213_scala212/input/word.txt")
        val wordRDD: RDD[String] = rdd.flatMap( _.split(" ") )
        val word2OneRDD: RDD[(String, Int)] = wordRDD.map((_,1))
        val word2GroupRDD = word2OneRDD.groupBy(_._1)
        val word2CountRDD = word2GroupRDD.map(s=>(s._1,s._2.size))
        word2CountRDD
    }

}

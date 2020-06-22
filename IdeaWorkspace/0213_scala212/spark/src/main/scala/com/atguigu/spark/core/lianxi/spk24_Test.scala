package com.atguigu.spark.core.lianxi

import org.apache.spark.util.{AccumulatorV2, LongAccumulator}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object spk24_Test{
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc : SparkContext = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List("hello","spark","hive","spark"),2)

    //todo 不使用distinct实现数据去重
    println("去重："+
      rdd
        .groupBy(s => s)
        .map(_._1)
        .collect()
        .mkString(",")
    )
    //todo 独立写出6种不同算子的WordCount
    println("==========Value 型==========")
    //todo  第一种
    println( "第一种--groupBy："+
      rdd
        .groupBy(s=>s)
        .map(s=>(s._1,s._2.size))
        .collect()
        .mkString(",")
    )
    //todo  第二种
    println( "第二种--mapValues："+
      rdd
        .map((_,1))
        .groupBy(s=>s._1)
        .mapValues( _.map(_._2).sum )
        .collect()
        .mkString(",")
    )

    println("==========Key--Value 型==========")

    //todo  第一种
    println( "第一种--groupByKey："+
      rdd
        .map((_, 1))
        .groupByKey()
        .map((s=>(s._1,s._2.sum)))
        .collect()
        .mkString(",")
    )

    //todo  第二种
   println( "第二种--reduceByKey："+
        rdd
          .map((_, 1))
          .reduceByKey(_ + _)
          .collect()
          .mkString(",")
      )
    //todo  第三种
    println("第三种--aggregateByKey:"+
      rdd
        .map((_, 1))
        .aggregateByKey(0)(_ + _,_+_)
        .collect()
        .mkString(",")
    )
    //todo  第四种
    println("第四种--foldByKey："+
      rdd
        .map((_, 1))
        .foldByKey(0)(_ + _)
        .collect()
        .mkString(",")
    )
    //todo  第五种
    println("第五种--combineByKey："+
      rdd
        .map((_, 1))
        .combineByKey(x=>x,(x:Int,y:Int)=>{
          x+y
        },(x:Int,y:Int)=>{
          x+y
        })
        .collect()
        .mkString(",")
    )
    println("==========  Action 型==========")
    //todo  第一种
    println( "第一种--countByKey："+
      rdd
        .map((_, 1))
        .countByKey()
    )
    //todo  第二种
    println( "第二种--countByValue："+
      rdd
        .countByValue()
    )
    //todo  第三种
    println( "第三种--reduce："+
      rdd
        .map(s=>Map[String,Int]((s,1)))
        .reduce(
          (map1,map2)=>{
            map1.foldLeft(map2)(
              (map,kv)=>{
                map.updated(kv._1,map.getOrElse(kv._1,0)+kv._2)  //产生新的 map
              }
            )
          }
        )
    )

    //todo  第四种
    println("第四种--aggregate："+
      rdd
        .aggregate(mutable.Map[String, Int]())(
          (m1, word) => {
            m1.update(word,m1.getOrElse(word,0)+1)
            m1
          },
          (m1, m2) => {
              m1.foldLeft(m2)(
                (map,kv) =>{
                  //              map(kv._1) = map.getOrElse(kv._1, 0) + kv._2
                  //              map                               //写法一
                  map.update(kv._1, map.getOrElse(kv._1, 0) + kv._2)
                  map                                 //写法二
                  }
              )
          }
        )
    )
    //todo  第五种
    println("第五种--fold："+
      rdd
      .map(s => mutable.Map(s -> 1))
      .fold(mutable.Map[String, Int]())(
        (m1, m2) => {
          //聚合
          m1.foldLeft(m2)(
            (map, kv) => {
//              map(kv._1) = map.getOrElse(kv._1, 0) + kv._2
//              map                               //写法一
              map.update(kv._1, map.getOrElse(kv._1, 0) + kv._2)
              map                                 //写法二
            }
          )
        }
      )
    )



    println("========== 累加器 ==========")
    //创建自定义累加器
    val acc = new HelloWordAcc()
    //向sc注册累加器
    sc.register(acc)
    //使用累加器
    rdd.foreach( acc.add(_))
    println("使用累加器："+acc.value)
    sc.stop()
  }
  class  HelloWordAcc extends AccumulatorV2[String,mutable.Map[String,Int]]{
    //返回值
    var map = mutable.Map[String,Int]()

    //是否初始化
    override def isZero: Boolean = map.isEmpty

    //复制累加器
    override def copy(): AccumulatorV2[String, mutable.Map[String,Int]] = new HelloWordAcc

    //重置累加器
    override def reset():Unit = map.clear()

    //累加
    override def add(word: String): Unit ={
      val i = map.getOrElse(word,0)
      map.update(word,1+i)
    }

    //收到Executor返回的累加的值
    override def merge(other: AccumulatorV2[String, mutable.Map[String, Int]]): Unit = {

        map=this.map.foldLeft(other.value)((mapDt,kv)=>{
//          //合并 ,更新方法一：mapDt(k)=v
//          mapDt(kv._1)=mapDt.getOrElse(kv._1,0)+kv._2
//          mapDt
          //合并,更新方法二：mapDt.update(k,v)
          val count =mapDt.getOrElse(kv._1,0)+kv._2
          mapDt.update(kv._1,count)
          mapDt
        })
    }

    //累加器的值
    override def value: mutable.Map[String, Int] = map
  }
}

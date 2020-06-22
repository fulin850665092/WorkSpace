package com.atguigu.spark.core.exem

import org.apache.spark.util.AccumulatorV2
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

/**
  *TODO 实现方案三： 使用累加器的方式聚合数据分
  */
object tes3_4 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("tes").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("0213_scala212/input/user_visit_action.txt")

    //创建累加器
    val acc = new WordCount
    //注册累加器
    sc.register(acc)
    //使用累加器
    rdd.foreach(acc.add(_))
    //获取累加器的值
      acc
        .value
//        .map(s=>(s._2.category,(s._2.clkNum,s._2.ordNum,s._2.payNum)))
        .map(_._2)
        .toList.
        sortWith(
          (lWord,rWord)=> {
              if (lWord.clkNum > rWord.clkNum){
                true
              }else if (lWord.clkNum == rWord.clkNum){
                  if(lWord.ordNum>rWord.ordNum){
                    true
                  }
                  else if (lWord.ordNum==rWord.ordNum){
                      lWord.payNum>rWord.payNum
                    }
                  else{
                      false
                  }
              }else{
                false
              }
          }
        )
        .take(10)
       .foreach(
          println
        )

    //todo 关闭
    sc.stop()
  }

  class  WordCount extends AccumulatorV2[String,mutable.Map[String,Word]] {

    private var wordMap: mutable.Map[String, Word] = mutable.Map[String, Word]()

    //初始化，判断是否为空
    override def isZero: Boolean = wordMap.isEmpty
    //累加器复制
    override def copy(): AccumulatorV2[String,  mutable.Map[String,Word]] = new WordCount()
    //重置累加器
    override def reset(): Unit = wordMap.clear()
    //累加
    override def add(line: String): Unit = {

      val lines = line.split("_")
      if(lines(6) != "-1"){
        var category=lines(6)
        val word = wordMap.getOrElse(category,Word(category,0,0,0))
        word.clkNum+=1
        wordMap.update(category,word)
        wordMap
      }                    //19
      else if(lines(8) != "null"){
        val categorys = lines(8).split(",")
        categorys.foreach(
          s=>{
            val word = wordMap.getOrElse(s,Word(s,0,0,0))
            word.ordNum+=1
            wordMap.update(s,word)
            wordMap
          }
        )

      }     //2,6,3,14
      else if(lines(10) != "null"){
        val categorys = lines(10).split(",")
        categorys.foreach(
          s=>{
            val word = wordMap.getOrElse(s,Word(s,0,0,0))
            word.payNum+=1
            wordMap.update(s,word)
            wordMap
          }
        )
      }    //15,1,20
      else wordMap

    }
    //收到Executor返回的累加的值
    override def merge(other: AccumulatorV2[String,  mutable.Map[String,Word]]): Unit = {
      this.wordMap=this.wordMap.foldLeft(other.value)(
        (map,kv)=>{
          val word = map.getOrElse(kv._1,Word(kv._1,0,0,0))
          word.clkNum+=kv._2.clkNum
          word.ordNum+=kv._2.ordNum
          word.payNum+=kv._2.payNum
          map.update(kv._1,word)
          map
        }
      )
    }
        //累加器返回值
    override def value:  mutable.Map[String,Word] = wordMap

  }

  case class Word(
               var category:String,
                var clkNum:Int,
                var ordNum:Int,
                var payNum:Int
              )
}

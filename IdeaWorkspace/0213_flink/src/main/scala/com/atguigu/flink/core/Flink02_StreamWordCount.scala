package com.atguigu.flink.core

import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment     //包含隐式转换

object Flink02_StreamWordCount {
  def main(args: Array[String]): Unit = {
    //创建Stream执行环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    //接收一个socket文本流
    val sckDs = env.socketTextStream("localhost",9999)
    //对每条数据处理
    sckDs
      .flatMap(_.split(" "))
      .map((_,1))
      .keyBy(0)
      .sum(1)
      .print()
      .setParallelism(3) //设置并行度,默认是当前电脑的核数


    //启动Executor
    env.execute("StreamWordCount")

  }
}

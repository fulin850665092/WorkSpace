package com.atguigu.flink.core

import org.apache.flink.api.scala._     //包含隐式转换

object Flink01_WordCount {
  def main(args: Array[String]): Unit = {
    //创建执行环境
    val env = ExecutionEnvironment.getExecutionEnvironment

    //从文件中读取数据
    val ds = env.readTextFile("0213_flink/input/word.txt")
    ds
      .flatMap(_.split(" "))
      .map((_,1))
      .groupBy(0)
      .sum(1)
      .print()


  }

}

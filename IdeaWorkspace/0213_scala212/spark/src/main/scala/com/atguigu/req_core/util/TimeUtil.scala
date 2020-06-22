package com.atguigu.req_core.util

import java.sql.Timestamp
import java.text.SimpleDateFormat

import org.json4s.scalap.{Failure, Success}

import scala.util.{Failure, Try}

object TimeUtil {
  def main(args: Array[String]): Unit = {
    println(calculatetimeGapSecond("2019-07-19 00:55:14", "2019-07-19 00:50:53"))
  }
  /** 比较两个时间相差秒 */
  def calculatetimeGapSecond(time1 :String ,time2: String ) ={
    val simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    val date1 = simpleDateFormat.parse(time1);
    val date2 = simpleDateFormat.parse(time2);
    date2.getTime() - date1.getTime();

  }

}

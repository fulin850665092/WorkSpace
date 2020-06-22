package com.atguigu.req_streaming.dao


import java.util.Random

import com.atguigu.req_streaming.bean.{CityInfo, RanOpt}
import com.atguigu.summer.framework.core.TDao

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class MockDataDao extends TDao{

  // TODO ********************数据准备*************************

  object RandomOptions {

    def apply[T](opts: RanOpt[T]*): RandomOptions[T] = {
      val randomOptions = new RandomOptions[T]()
      for (opt <- opts) {
        randomOptions.totalWeight += opt.weight
        for (i <- 1 to opt.weight) {
          randomOptions.optsBuffer += opt.value
        }
      }
      randomOptions
    }
  }

  class RandomOptions[T](opts: RanOpt[T]*) {

    var totalWeight = 0
    var optsBuffer = new ListBuffer[T]

    def getRandomOpt: T = {
      val randomNum: Int = new Random().nextInt(totalWeight)
      optsBuffer(randomNum)
    }
  }
  // TODO ********************数据生成*************************
  implicit def genMockData() : Seq[String] = {
    val array: ArrayBuffer[String] = ArrayBuffer[String]()
    val CityRandomOpt = RandomOptions(RanOpt(CityInfo(1, "北京", "华北"), 30),
      RanOpt(CityInfo(2, "上海", "华东"), 30),
      RanOpt(CityInfo(3, "广州", "华南"), 10),
      RanOpt(CityInfo(4, "深圳", "华南"), 20),
      RanOpt(CityInfo(5, "天津", "华北"), 10))

    val random = new Random()
    // 模拟实时数据：
    // timestamp province city userid adid
    for (i <- 0 to 50) {

      val timestamp: Long = System.currentTimeMillis()
      val cityInfo: CityInfo = CityRandomOpt.getRandomOpt
      val city: String = cityInfo.city_name
      val area: String = cityInfo.area
      val adid: Int = 1 + random.nextInt(6)
      val userid: Int = 1 + random.nextInt(6)

      // 拼接实时数据
      array += timestamp + " " + area + " " + city + " " + userid + " " + adid
      println(array)
    }
    array.toSeq
  }

}

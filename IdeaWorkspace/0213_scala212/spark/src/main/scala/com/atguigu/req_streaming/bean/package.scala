package com.atguigu.req_streaming

package object bean {
  /**
    *
    * 城市信息表
    *
    * @param city_id     城市id
    * @param city_name   城市名称
    * @param area        城市所在大区
    */
  case class CityInfo (city_id:Long,
                       city_name:String,
                       area:String)

  case class RanOpt[T](value: T, weight: Int)

  case class Ads_log(dt: String,
                     area: String,
                     city: String,
                     userid: String,
                     adid: String)

}

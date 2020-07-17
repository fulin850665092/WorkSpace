package com.atguigu.dw.gmall

package object realtime {
  case class StartupLog(mid: String,
                        uid: String,
                        appId: String,
                        area: String,
                        os: String,
                        channel: String,
                        logType: String,
                        version: String,
                        ts: Long,
                        var logDate: String,
                        var logHour: String)
  case class OrderInfo(id: String,
                       province_id: String,
                       var consignee: String,
                       order_comment: String,
                       var consignee_tel: String,
                       order_status: String,
                       payment_way: String,
                       user_id: String,
                       img_url: String,
                       total_amount: Double,
                       expire_time: String,
                       delivery_address: String,
                       create_time: String,
                       operate_time: String,
                       tracking_no: String,
                       parent_order_id: String,
                       out_trade_no: String,
                       trade_body: String,
                       var create_date: String,
                       var create_hour: String)

}

package com.atguigu.canal

import java.net.InetSocketAddress
import java.util

import com.alibaba.fastjson.JSONObject
import com.alibaba.otter.canal.client.{CanalConnector, CanalConnectors}
import com.alibaba.otter.canal.protocol.CanalEntry.EventType
import com.alibaba.otter.canal.protocol.{CanalEntry, Message}
import com.atguigu.dw.gmall.common.constant.GmallConstant



object CanalClientScala {
  def main(args: Array[String]): Unit = {
    val connector = CanalConnectors.newSingleConnector(new InetSocketAddress("hadoop202",11111),"example","","")
    while(true){
      connector.connect()
      connector.subscribe("gmall_realtime.*")
      val message = connector.get(1024) //最多拉取由于1024 条导致变化的数据
      if (message.getEntries.size()==0){
        println("没有数据，休息一会！")
        Thread.sleep(3000)
      }else{
          message.getEntries.forEach(
            dt=>{
              //只取EntryType是ROWDATA的类型
              if (dt!=null && CanalEntry.EntryType.ROWDATA==dt.getEntryType){
                val tableName = dt.getHeader.getTableName
                val rowChange = CanalEntry.RowChange.parseFrom(dt.getStoreValue)
                val rowDatasList = rowChange.getRowDatasList
                val eventType = rowChange.getEventType
                handler(tableName,rowDatasList,eventType)
              }
            }
          )
      }
    }

  }

  def handler(tableName: String, rowDatasList: util.List[CanalEntry.RowData], eventType: EventType) = {
    if ("order_info" == tableName && CanalEntry.EventType.INSERT == eventType) {
      rowDatasList.forEach(
        rowData => {
          val jSONObject = new JSONObject
          rowData.getAfterColumnsList.forEach(
            column => {
              jSONObject.put(column.getName, column.getValue)
            }
          )
          println(jSONObject.toString)
          MyKafkaSender.send(GmallConstant.GMALL_ORDER_INFO,jSONObject.toString)
        }
      )
    }
  }
}

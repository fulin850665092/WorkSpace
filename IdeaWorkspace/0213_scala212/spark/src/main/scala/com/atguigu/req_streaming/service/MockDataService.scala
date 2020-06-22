package com.atguigu.req_streaming.service

import com.atguigu.req_streaming.dao.MockDataDao
import com.atguigu.summer.framework.core.TService

class MockDataService extends TService{
  private val mockDataDao = new MockDataDao


  override def analysis(): Any = {
    //生成模拟数据
    val datas: Seq[String] = mockDataDao.genMockData()
//    import mockDataDao._
    //将数据写入kafka
    mockDataDao.writeKafka(datas)
  }


}

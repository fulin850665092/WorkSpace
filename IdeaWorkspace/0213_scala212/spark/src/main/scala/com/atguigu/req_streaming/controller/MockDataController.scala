package com.atguigu.req_streaming.controller

import com.atguigu.req_streaming.service.MockDataService
import com.atguigu.summer.framework.core.TController

class MockDataController extends  TController{

  private val service = new MockDataService
  override def executor(): Unit = {
    service.analysis()
  }
}

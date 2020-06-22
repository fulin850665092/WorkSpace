package com.atguigu.req_streaming.controller

import com.atguigu.req_streaming.service.BlackListServcie
import com.atguigu.summer.framework.core.TController

class BlackListController extends TController{
  private val blackListServcie = new BlackListServcie

  override def executor(): Unit = {
//    blackListServcie.analysis()
//    blackListServcie.analysis1()
    blackListServcie.analysis2()
  }
}

package com.atguigu.scala

import scala.util.control.{BreakControl, Breaks}

class start {

  def st(op: => Unit) {
      try {
        op
      } catch {
        case ex: Exception => println("执行失败！")

      }
  }
}

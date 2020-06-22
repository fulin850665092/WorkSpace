package com.atguigu.collection

import scala.collection.mutable.ListBuffer

object  coll5_seq {
  def main(args: Array[String]): Unit = {
      //序列
    // 空集合：Nil
    //主要用于添加数据
    println(Nil)
    val list = 1::2::3::4::Nil
    //等同：Nil.::(4)::(3)::(2)::(1)
    println(list.mkString(","))
    val list1=4::5::list::Nil
    println(list1.mkString(","))
    //todo 扁平化：将一个整体拆分成个体来使用
    val list2=4::5::list:::Nil
    println(list2.mkString(","))
    //可变序列
    val listBuffer = ListBuffer(1,2,3,4)
    //追加
    listBuffer.append(4,5,6)
    //插入
    listBuffer.insert(4,8,9,0)
    //修改
    val listBuffer2 = listBuffer.updated(2,23)
    //删除
    listBuffer.remove(4,3)

    println(listBuffer.mkString(","))
    println(listBuffer2.mkString(","))

  }
}

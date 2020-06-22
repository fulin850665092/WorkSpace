package com.atguigu.lianxi

object lianxi7 {
  def main(args: Array[String]): Unit = {
    val list = List(
      ("zhangsan", "河北", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "鞋"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "鞋"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "鞋"),
      ("zhangsan", "河北", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "帽子"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "电脑"),
      ("zhangsan", "河南", "鞋"),
      ("lisi", "河南", "衣服"),
      ("wangwu", "河南", "电脑"),
      ("zhangsan", "河南", "电脑"),
      ("lisi", "河北", "衣服"),
      ("wangwu", "河北", "帽子")
    )
    println(
      list
        .map(s=>((s._2+"-"+s._3,1)))
        .groupBy(s=>s._1)
        .mapValues(_.size)
        .toList
        .sortBy(_._2)(Ordering.Int.reverse)
        .map(s=>{(s._1.split("-")(0)+"",(s._1.split("-")(1)+" "+s._2))})
        .groupBy(_._1)
        .mapValues(_.map(_._2))
    )
  }
}

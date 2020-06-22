package com.atguigu.scala

object yunsuan {
  def main(args: Array[String]): Unit = {
      val  s1:Byte=1
      val s2:Short=2
      val s3:Int=3
      val s4:Long=3l
      val s5:Float=3.5f
      val s6:Double=3.5d
      val s7:Boolean=true
      val s8:Char='a'
      val s9:Int=s1
      val s10:Int =s8
      println(s10)
      println( s2.toString)
      val nil:List[Nothing]=Nil
      val s12:Any="s"
      val s13:Any=1
      val s14:AnyRef="1"
      val s15:AnyVal=1
      //可以编译，可以运行（常量的计算在编译时运行，变量参与运算要提升数据类型）
      val c : Char = 'A' + 1
      println(c)
      val s16: String = "sss"
      val s17:Int=10
      println(s17.toString)
      println(s17+"")
      println(s17.+(s1))
      println(s17+s1)

    val a = new String("abc")
    val b = new String("abc")
    //比较的是内容（加了null判断，底层也是equals，（java==比较的是内存，其他的比较的是内容）
    println(a == b)
    //比较的是内容
    println(a.equals(b))
    //比较的是地址值
    println(a.eq (b))
    //数字也是对象，万物皆对象
    println(1.to(2))
    println("abc".*(2))
    val user =new user
//    user.xx("sssss")
//    user xx "sssss"
    val result =forTes(69)
    println(result)
    val result1=sanyuan(13)
    println(result1)

    for (elem <- 1 to 10 by 2 ) {
      println(elem)
    }
    println("=================")
    for (elem <- 1 until 10 by 2) {
      println(elem)
    }
    println("=================")
    for (elem <- Range(1, 10, 2)) {
      println(elem)
    }
  }
  def test()={
     throw new Exception
  }

  def forTes(va:Int)={
    if (va<10){
        "我小于10"
    }else if(va <29){
        "我年纪10-29"
    }else{
        "我大于29"
    }
  }
  def sanyuan(num:Int)={
     if (num<10) "小于十岁" else "大于十岁"
  }
}

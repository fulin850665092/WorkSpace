package com.atguigu.trait_tes
object tes4{
  def main(args: Array[String]): Unit = {
    //初始化顺序： 混入顺序：上到下，左到右
    //功能执行顺序：
        //将特质之间见了关系，然后在调用时形成功能叠加（没有关系则会报错）
      //从右到左，下到上
    val user =new User1()
    user.test()
  }
}
trait parentest{
  def test()={
    println("parentest")
  }
}
trait tes4 extends parentest {
//  println("tes4")
  super.test()
  override def test(): Unit = {
    println("tes4")
  }

}
trait tes5 extends parentest {
//    println("tes5")
  super.test()
  override def test(): Unit = {
    println("tes5")
  }
}

 class User1 extends tes4 with tes5{
//   override def test(): Unit = {
//     println("user test....")

//   }
 }
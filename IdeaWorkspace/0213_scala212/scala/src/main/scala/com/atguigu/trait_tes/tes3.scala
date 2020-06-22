package com.atguigu.trait_tes

object tes3{
  def main(args: Array[String]): Unit = {
      //构造顺序：父类-》父特质-》子特质-》子类
      new tes2Class()
  }
}
trait parent{
  println("parent trait")
}
trait tes3 extends parent {
  println("child trait")
}
class parentClass {
  println("parent Class")
}
class tes2Class extends  parentClass with tes3 {
  println("child Class")
}
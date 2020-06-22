package com.atguigu.trait_tes

object tes3_1 {
  def main(args: Array[String]): Unit = {
    //构造顺序：父类-》父特质-》子特质-》子类
    //构造顺序：父特质-》父类-》子特质-》子类
    new tes2Class1()
  }
}
trait parent1{
  println("parent trait")
}
trait tes31 extends parent1 {
  println("child trait")
}
class parentClass1 extends  parent1{
  println("parent Class")
}
class tes2Class1 extends  parentClass1 with tes31 {
  println("child Class")
}
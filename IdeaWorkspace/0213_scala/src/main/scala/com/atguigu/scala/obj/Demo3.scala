package com.atguigu.scala.obj

object Demo3 {
  def main(args: Array[String]): Unit = {
    val circle=new Circle(0,0,4)
    val circle1=new Circle(0,4,6)
    println(circle.distance(circle1))
    println(circle.area)
  }
}
class Point(val x: Double,val y:Double){
  def distance(other:Point)={
    math.sqrt((this.x-other.x)*(this.x-other.x)+(this.y-other.y)*(this.y-other.y))
  }
}

class  Circle(override val x:Double, override  val y:Double,val r:Double ) extends Point(x,y){
  override  def toString:String=s"x:$x,y:$y,r:$r"
  def area=math.Pi*r*r
}
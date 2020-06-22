package com.atguigu.match1

object mat7 {
  //对象的模式匹配
  def main(args: Array[String]): Unit = {
      val user1 = User88("zhangsan",20)
      val user2 = new User88("zhangsan",20)

      user2 match {
            //必须重写 object User88 的unapply方法
        case User88("lisi",20) => println("User is user")
        case _=> println("other")
      }
  }
  class  User88(val name:String,val age:Int)
   object User88 {
     //param=> object
     def apply( name: String, age: Int): User88 = new User88( name,age)
     //object=> param
     def unapply(arg: User88): Option[(String, Int)] = {
       if(arg==null) None else Some((arg.name,arg.age))
     }
   }
}



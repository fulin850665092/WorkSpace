package com.atguigu.danli

object danlie1 {
  def main(args: Array[String]): Unit = {
    //todo 面向对象编程， -单例
      //1。将类的构造方法私有化：将peivate 关键字声明在类的参数列表前面
//      new User
      //2。在伴生对象中的内部创建一个公共的方法，用于构建对象
//       val user =User.getInstance()
        //3。伴生对象本身就是单列代谢， 如果属性是对象，直接创建就是单列

        //4。如果类的构造器方法私有化，那么就可以采用伴生对象来构建对象
//          val user =User.createInstance()
        //5。通过方法构造对像不方便，并且有歧义，所有编译器可以简化这个过程

        //6。如果方法名就apply，那么就可以省略方法名称
          val user=User()
          println(user)
        //todo 构建对象的两种方式
          // todo 1。 使用new 关键字构造，相当于调用构造方法
//              val user1 =new User()
          //todo 2。使用伴生对象构造对象，等同于调用伴生对象的apply方法(括号不能省略)
//              val user=User()
          //todo 3。获取伴生对象的类，不会调用apply（）
//                  val user =User   (获取的是伴生类)
  }

  class  User private(){
  }
  object  User{
//    private var user =null
//    def getInstance()={
//      if(user==null) new User() else user
//    }
    def apply()=new User()
    def apply(name:String)=new User()
  }
}

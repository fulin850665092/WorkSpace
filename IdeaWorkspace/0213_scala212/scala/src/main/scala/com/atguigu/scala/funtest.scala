package com.atguigu.scala

object funtest {
  def main(args: Array[String]): Unit = {
    def tes()={
        println("我是函数")
    }
    tes()
    println("=================")

    def tes2()={
      println("无参数，无返回值")
    }
    tes2()
    println("=================")

    def tes3(num:Int)={
      println(s"有参数+$num，无返回值")
    }
    tes3(3)
    println("=================")

    def tes4(num :Int):String={
      s"有参数：$num，有返回值。"
    }
    println(tes4(4))
    println("=================")

    def tes5(num1:Int,num2:Int)={
      println(s"多参数：$num1;$num2，无返回值")
    }
    tes5(1,2)
    println("=================")

    def tes6(num1:Int,num2:Int)={
     s"多参数：$num1;$num2，有返回值"
    }
    println(tes6(1,2))
    println("=================")

    def tes7(num:Int*): Unit ={
      println(s"可变参数：$num，不能超过20个参数")
    }
    tes7(1,2,3,4,6,7)
    println("=================")

    def tes8(str:String,num:Int*): Unit ={
      println(s"参数：$str,可变参数：$num，放最后")
    }
    tes8("我是不可变参数",2,3,4,6,7)
    println("=================")

    def tes9(a:Int,b:Int=10,c:Int)={
      a+b+c
    }
    println(tes9(1,c=19))
    println(tes9(2,3,4))
    println("=================")

//    def fun1():String ={
//      return  "sss"
//    }
    println("========至简原则=========")
    def fun1 = "sss"
    println(fun1)

    //TODO 1。函数参数为空，或者只有一个函数，（）可以省略
    //TODO 2。函数体中只有一行代码，大括号可以省略
    //TODO 3。方法体类型可推断，返回值类型，和return可以省略
    //TODO 4。函数体中有明确的return语句，返回值类型不能省略
     def fun2:String =  return  "sss"
    //TODO 5。明确返回值是Unit，返回值类型和=都可省略，retrun 无效
      def fun5() {
        return "zhangsan"
      }
    //TODO 6。匿名函数,函数可以作为值传给对象加 _s
     val fun=(i:Int)=>{
       print("我是匿名函数")
     }
    //直接打印对象名，是接收函数的返回值
     println(fun)
    //加()则是接收的函数整体
     println(fun(2))

    def fun3()={
      "我是函数,来接收我的值"
    }
    //接收的是函数返回值
    val fw=fun3
//    fw() //erro
    //接收的函数的整体
    val fw1=fun3 _
    println(fw1())
    //将变量的函数类型声明为函数类型也可以接收
     //函数类型：参数列表=>返回值类型  例如：(Int，String)=>Stirng
    val fw2:()=>String =fun3
    println(fw2())

    def foo(a:Int)=  a*a
    val fw3:(Int)=>Int=foo _
    println(fw3(2))

    println("======= 函数作为参数传递 =========")
    def ar(b:(Int)=>Int)={
      b(19)
    }
    def b1(c:Int):Int={
      c*2
    }
    val de =b1 _
    println(ar(de))
    println(ar(b1 _))
    //匿名函数作为参数
//    println(ar((i:Int)=> i+2))
    // 至简原则,如果参数在逻辑代码中只使用了一次，那么参数和=>可以省略。使用_代替参数
      println(ar(_+2))

      //求和
      def boyCouAndgirl(t:(Int,Int)=>Int)={
        val boy=28
        val girl=22
        t(boy,girl)
      }
      //方法一：
      def sum(x:Int,y:Int)={
        x+y
      }
      def f=sum _
      println(f)

      //方法二：
      println("======= 使用匿名函数代替f=========")
//      println(boyCouAndgirl((x:Int,y:Int)=>{ x+y
//      }))
      //至简原则
        //1
//        println(boyCouAndgirl((x:Int,y:Int)=>x+y))
        //2至简原则,如果参数在逻辑代码中只使用了一次，那么参数和=>可以省略。使用_代替参数
        println(boyCouAndgirl(_ + _))

      def f3(i:Int):Int={
        i*2
      }
      println(f3(10))
    //将函数作为整体发挥（ _）
    def f33()={
      f3 _
    }
    val v3=f33()
    println( v3(20))
    //明确返回值类型是 函数类型，可以不用_
    def f333():Int=>Int={
      f3
    }
    println(f333()(30))
    //嵌套函数
    def f3333()={
      def f44(i:Int)={
        i*40
      }
      f44 _
    }
    println(f3333()(2))
    //闭包：一个变量函数在执行完后，被其他的函数包含使用，改变了这个函数的声明周期，形成了一个闭合的效果
     //1。嵌套函数的使用（要使用），就有闭包
     //2。将函数赋值给变量来使用，也会有闭包
    def f5()={
      val i= 10
      def f51():Int={
        i*2
      }
      f51 _
    }
    println(f5()())

    //函数柯里化
    //实现：tes(10)(20)
    def te22(ii:Int)={
      def te33(jj:Int)={
          ii*jj
      }
      te33 _
    }
    println(te22(10)(20))
    //实现：tes(10)(20)(_+_)
    def w1(w1:Int)={
        def w2(w2:Int)={
              def w3(f:(Int,Int)=>Int):Int={
                  f(w1,w2)
              }
               w3 _
        }
        w2 _
    }
    println(w1(10)(20)(_+_))
    println(w1(10)(20)(_*_))

    //柯里化:将复杂的函数参数列表变得简单化
    //将多个参数的列表变成哟个参数列表
    def ww1(d:Int)(d2:Int)(f:(Int,Int)=>Int):Int={
      f(d,d2)
    }
     println(ww1(10)(20)(_+_))
//    val xxx:Int=1+2+...+99
//    val yyy:Int=1+.....+999
//    val zzz:Int=1+....9999
//    def sumtes(xxx:Int)(yyy:Int)(zzz:Int):Int={
//
//    }

  }
}

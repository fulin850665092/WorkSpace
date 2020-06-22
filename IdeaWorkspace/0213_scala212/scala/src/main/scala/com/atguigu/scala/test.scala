package com.atguigu.scala

object test{

    def main(args:Array[String]):Unit={
        println("自己写的比较香")
        //我是静态的，我被调用了
//        println(test.tests3333())
//        // 也可以直接调用
//        println(tests3333())

        val ii=   fun{
            println("函数抽像")
            ""
        }
    }

//    def tests3333():Unit={
//      "有我就够了，不用return"
//    }

    def fun(op : => String)={
        op
    }

}

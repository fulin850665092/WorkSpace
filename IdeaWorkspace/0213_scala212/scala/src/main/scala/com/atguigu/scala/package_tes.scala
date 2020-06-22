package com
package atguigu
package  scala
//等价：com.atguigu.scala
package xxxx{
//  new User()
  class User{
  }
  package tes{
    object tes2P{
      new User
    }
    package tes2{
        object tes2P{
          new User
        }
    }
  }
}
object package_tes {
}


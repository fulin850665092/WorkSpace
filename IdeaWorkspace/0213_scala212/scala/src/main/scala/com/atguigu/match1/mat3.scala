package com.atguigu.match1

object mat3 {
    def main(args: Array[String]): Unit = {
      var a: Int = 10
      var b: Int = 20
      var operator: Char = '*'
      var result = operator match {
        case '+' => a + b
        case '-' => a - b
        case '*' => a * b
        case '/' => a / b
        case _ => "illegal"
      }
      println(result)
      //匹配常量
      println(describe(99))
    }
  def describe(x: Any) = x match {
    case 5 => "Int five"
    case "hello" => "String hello"
    case true => "Boolean true"
    case '+' => "Char +"
    case _ => "null"
  }

  }

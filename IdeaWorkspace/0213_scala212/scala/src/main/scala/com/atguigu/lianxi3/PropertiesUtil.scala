package com.atguigu.lianxi3

import java.util.ResourceBundle


object  PropertiesUtil {

    val summer=ResourceBundle.getBundle("fenbushi")

    def getValue(key:String):String={
        summer.getString(key);
    }
}

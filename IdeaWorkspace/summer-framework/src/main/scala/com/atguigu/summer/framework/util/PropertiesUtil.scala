package com.atguigu.summer.framework.util

import java.util.ResourceBundle


object  PropertiesUtil {

    val summer=ResourceBundle.getBundle("summer")

    def getValue(key:String):String={
        summer.getString(key);
    }

}

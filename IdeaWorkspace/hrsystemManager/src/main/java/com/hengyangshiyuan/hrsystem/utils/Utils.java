package com.hengyangshiyuan.hrsystem.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;


public class Utils {

	/**
	 * 在api的参数中，尽量使用javaSE的类。
	 * 
	 * @param params
	 * @param user
	 */
	public static <T> T copyParam2Bean(Map params, T user) {
		try {
			// populate会把 map中的值，注入到user对象中
			BeanUtils.populate(user, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	/**
	 * 将字符串转换成为int返回
	 * 
	 * @param intStr
	 * @param defaultValue
	 * @return
	 */
	public static int parseInt(String intStr, int defaultValue) {
		int result = 0;
		try {
			result = Integer.parseInt(intStr);
		} catch (Exception e) {
			// e.printStackTrace();
			result = defaultValue;
		}
		return result;
	}

	public static double parseDouble(String doubleStr, double defaultValue) {
		double result = 0;
		try {
			result = Double.parseDouble(doubleStr);
		} catch (Exception e) {
			result = defaultValue;
		}
		return result;
	}

}

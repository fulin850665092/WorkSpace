package com.hengyangshiyuan.hrsystem.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class UUIDUtil {	
 public static String getFourRandom(){
	 Random random=new Random();
	 int nextInt = random.nextInt(10000);
	 String fourRandom=nextInt+"";
	 int randLength = fourRandom.length();
	 if (randLength<4) {
		for (int i = 0; i <4-randLength; i++) {
			fourRandom="0"+fourRandom;
		}
	}
	 return fourRandom;
 }
 public static String getZZID() {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	String str1 = sdf.format(new Date());
	String str2 = getFourRandom();
	return "FCG"+str1+str2;
 }
// public static void main(String[] args) {
//	System.out.println(getZZID());
//}
}

package com.qa.api.utils;

public class StringUtility {
	//select * from user where email like '%myapiautomation%'
	public static String getRandomEmailId() {
		return "myapiautomation"+System.currentTimeMillis()+"@gmail.com";
	}

}

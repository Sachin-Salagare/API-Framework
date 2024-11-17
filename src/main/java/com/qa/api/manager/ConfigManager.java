package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


//properties class from collection which will help to load property from--- java.util package
public class ConfigManager {

	private static Properties properties=new Properties();

	static {//creating static block
		//hear using reflection in concept in java
		try(InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config/config.properties")){
			if(input!=null) {
				properties.load(input);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}
	public static void set(String key,String value) {
		 properties.setProperty(key, value); // FW cl 3 -1.13.3
	}	
}

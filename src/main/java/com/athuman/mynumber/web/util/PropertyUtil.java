package com.athuman.mynumber.web.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	
	public static String getProperties(String propertiesFile, String key) {

		// read properites file:
		Properties props = new Properties();
		InputStream in = AESUtil.class
				.getResourceAsStream("/" + propertiesFile);
		try {
			props.load(in);
		} catch (Exception e) {
			return null;
		}
		return props.getProperty(key);
	}
}

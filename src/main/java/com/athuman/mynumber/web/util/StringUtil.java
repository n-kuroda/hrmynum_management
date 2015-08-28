package com.athuman.mynumber.web.util;

public class StringUtil {

	
	public static boolean isNotEmpty(String value) {
		return value != null && !value.equals("");
	}
	
	public static boolean isEqual(Object object1, Object object2) {
		return object1.equals(object2);
	}
}

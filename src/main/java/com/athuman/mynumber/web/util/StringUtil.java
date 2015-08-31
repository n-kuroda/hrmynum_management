package com.athuman.mynumber.web.util;

import java.util.regex.Pattern;

public class StringUtil {

	private static final Pattern PATTERN_ILLEGAL_1BYTE_CHAR = Pattern.compile("^^[a-zA-Z-0-9]*$");

	/** check input value is 1 byte character or not */
	public static boolean isValid(CharSequence value) {

		if (value == null || value.length() < 1) {
			return true;
		}
		return PATTERN_ILLEGAL_1BYTE_CHAR.matcher(value).find();
	}
	
	public static boolean isNotEmpty(String value) {
		return value != null && !value.equals("");
	}
}

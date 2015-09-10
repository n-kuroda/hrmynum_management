package com.athuman.mynumber.web.util;

import java.io.IOException;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {

	/** write object as josn type */
	public static String toJson(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String value = mapper.writeValueAsString(object);
			return value;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
}

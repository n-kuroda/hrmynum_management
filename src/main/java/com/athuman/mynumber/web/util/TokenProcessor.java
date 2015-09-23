package com.athuman.mynumber.web.util;


import java.util.UUID;

import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

public class TokenProcessor {
	
	private static final String SESSION_TOKEN_KEY = TokenProcessor.class.getName();

	public static boolean isTokenValid(WebRequest request, String requestToken) {
		if(!StringUtil.isNotEmpty(requestToken)){
			return false;
		}
		final String sessionToken = getCurrentToken(request);
		if (!StringUtil.isNotEmpty(sessionToken)) {
			return false;
		}
		if (sessionToken.equals(requestToken)) {
			removeToken(request);
			return true;
		} else {
			return false;
		}
	}

	private static void removeToken(WebRequest request) {
		request.removeAttribute(SESSION_TOKEN_KEY, RequestAttributes.SCOPE_SESSION);
	}

	private static String getCurrentToken(WebRequest request) {
		return (String) request.getAttribute(SESSION_TOKEN_KEY, RequestAttributes.SCOPE_SESSION);
	}
	
	public static void saveToken(WebRequest request, Model model) {
		String token = UUID.randomUUID().toString();
		request.setAttribute(SESSION_TOKEN_KEY, token, RequestAttributes.SCOPE_SESSION);
		model.addAttribute("token", token);
	}

}

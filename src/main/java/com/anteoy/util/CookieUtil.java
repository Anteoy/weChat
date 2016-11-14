package com.anteoy.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {

	public static String getCookie(HttpServletRequest request, String key) {
		if (key == null || key.length() == 0) return null;
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (key.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}
		return null;
	}

}

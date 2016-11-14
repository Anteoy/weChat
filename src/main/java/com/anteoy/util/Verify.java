/*
package com.anteoy.util;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Verify {

	private static Logger logger = Logger.getLogger(Verify.class);
	private static DateMorpher dateMorpher = new DateMorpher(new String[] {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"});
	private static TimestampMorpher timestampMorpher = new TimestampMorpher();

	*/
/**
	 * 获取会话是否存活状态
	 * @param sessionId
	 * @param verifyUrl
	 * @param verifyId
	 * @return
	 *//*

	public static String getAlive(String sessionId, String verifyUrl, String verifyId) {
		if (verifyUrl == null || verifyId == null) return null;
		HttpContext context = new HttpContext();
//		if (baseUrl != null) {
			if (verifyUrl.charAt(verifyUrl.length()-1) == '/') verifyUrl = verifyUrl.substring(0, verifyUrl.length()-1);
//		} else {
//			baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/system";
//		}
		context.url = verifyUrl + "/isAlive";
		context.cookie = "JSESSIONID=" + sessionId + "; " + "JVERIFYID=" + verifyId;
		try {
			HttpConnect.connect(context);
			if (context.response == null || context.response.length == 0) return null;
			String jvid = new String(context.response);
			return jvid;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	*/
/**
	 * 获取用户对象信息
	 * @param sessionId
	 * @param verifyUrl
	 * @param verifyId
	 * @return
	 *//*

	public static UserInfo getUserInfo(String sessionId, String verifyUrl, String verifyId) {
		if (verifyUrl == null || verifyId == null) return null;
		HttpContext context = new HttpContext();
//		if (verifyUrl != null) {
			if (verifyUrl.charAt(verifyUrl.length()-1) == '/') verifyUrl = verifyUrl.substring(0, verifyUrl.length()-1);
//		} else {
//			verifyUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/system";
//		}
		context.url = verifyUrl + "/verify";
		context.cookie = "JSESSIONID=" + sessionId + "; " + "JVERIFYID=" + verifyId;
		try {
			HttpConnect.connect(context);
			if (context.response == null || context.response.length == 0) return null;
			String userJson = new String(context.response, "UTF-8");
			logger.info(userJson);
			JSONUtils.getMorpherRegistry().registerMorpher( dateMorpher );
			JSONUtils.getMorpherRegistry().registerMorpher( timestampMorpher );
			UserInfo userinfo = (UserInfo) JSONObject.toBean(JSONObject.fromObject(userJson), UserInfo.class);
			return userinfo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	*/
/**
	 * 从request中获取认证ID
	 * @param request
	 * @param response 
	 * @return
	 *//*

	public static String GetJVerifyId(HttpServletRequest request, HttpServletResponse response) {
		String jVerifyId = request.getParameter("jVerifyId");
		if (jVerifyId != null && jVerifyId.length() > 0) {
			Cookie cookie = new Cookie("JVERIFYID", jVerifyId);
			cookie.setPath("/");
			response.addCookie(cookie);
		} else { // 未从request中获取到验证id, 尝试从cookies中获取
			Cookie[] cookies = request.getCookies();
			if (cookies == null || cookies.length == 0) return null;
			for (int i = 0; i < cookies.length; i++) {
				if ("JVERIFYID".equals(cookies[i].getName())) {
					jVerifyId = cookies[i].getValue();
					break;
				}
			}
		}
		if (jVerifyId != null && jVerifyId.length() == 0) jVerifyId = null;
		return jVerifyId;
	}

}
*/

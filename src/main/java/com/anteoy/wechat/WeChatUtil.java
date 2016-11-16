package com.anteoy.wechat;


import com.anteoy.util.FileUtil;
import com.anteoy.wechat.entity.*;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

public class WeChatUtil {
	
	/** 接口调用凭证 */
	public static AccessToken ACCESS_TOKEN = null;
	
	public static JsapiTicket JSAPI_TICKET = null;
	
	/** 应用ID */
	public static final String APPID = "wx125f0e830661063b";
	
	/** 应用密钥 */
	private static final String APPSECRET = "9a4893127358441cbae4734ede4f9558";
	
	/** 创建菜单接口 */
	private static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/** 获取ACCESS_TOKEN接口 */
	private final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/** 获取JSAPI_TICKET接口 */
	private final static String JSAPI_TICKET_URl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	/** 创建分组接口 */
	private static String GROUP_CREATE = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";

	/** 移动用户到指定组接口 */
	private static String MOVE_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";

	private static String DELETE_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN";

	private static String QUERY_GROUP = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";

	/** 设置个性化菜单 */
	private static String TEST_ADDITIONAL_MENU = "https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=ACCESS_TOKEN";

	/** 设置所属行业 */
	private static String SHEZSSHY = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";

	/** 获得模板消息id */
	private static String HUODMBXXID = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";

	/** 发送模板消息 */
	private static String FASMBXX = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	/** 发送普通信息 */
	private static String FASPTXX = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

	/** 创建个性化菜单接口 */
	public static String ADDITIONALMENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN";

	static { 
		ACCESS_TOKEN = new AccessToken();
		JSAPI_TICKET = new JsapiTicket();
	}

	
	/* 发送请求到微信服务器，获取返回信息 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();

			jsonObject = JSONObject.fromObject(buffer.toString());
			if ( !requestUrl.contains("&appid") ) {
				if (
					(jsonObject.has("errcode") && jsonObject.getInt("errcode") == 40001) || 
					(jsonObject.has("errmsg") && jsonObject.getString("errmsg").contains("access_token is invalid"))
				) {
						String newUrl = requestUrl.substring(0, requestUrl.indexOf("=")+1);
						WeChatUtil.ACCESS_TOKEN.setToken(null);
						newUrl = newUrl + getAccessToken().getToken();
						return httpRequest(newUrl,requestMethod,outputStr);
				}
			}
		} catch (ConnectException ce) {
			ce.printStackTrace();
			System.out.println("微信服务器连接超时！");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("HTTPS请求错误，错误信息：\n" + e.getMessage());
		}
		return jsonObject;
	}

	public static void main(String[] args) {
		WeChatUtil.getAccessToken();
	}

	/**
	 * 获取access_token
	 * @return
	 */
	public static AccessToken getAccessToken() {
		
		if (null == ACCESS_TOKEN.getToken() || ACCESS_TOKEN.getToken().equals("") || !checkAccessToken()) {
			String requestUrl = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
			// 如果请求成功
			if (null != jsonObject) {
				try {
					ACCESS_TOKEN.setToken(jsonObject.getString("access_token"));
					
					ACCESS_TOKEN.setExpiresIn(System.currentTimeMillis());
				} catch (JSONException e) {
					ACCESS_TOKEN.setToken(null);
					// 获取token失败
					System.out.println("获取TOKEN失败("+jsonObject.getInt("errcode")+")："+ WeChatErrorCode.ERRORCODE.get(jsonObject.getInt("errcode")));
				}
			}
		}
		System.out.println("全局ACCESS_TOKEN:   "+ACCESS_TOKEN.getToken());
		return ACCESS_TOKEN;
	}

	public static boolean checkAccessToken() {
		boolean flag = System.currentTimeMillis() - ACCESS_TOKEN.getExpiresIn() >=7000*1000 ? false:true;
		System.out.println("距离上次获取access_token过去 "+(System.currentTimeMillis() - ACCESS_TOKEN.getExpiresIn())+"毫秒");
		return flag;
	}

	/**
	 * 单例模式获取jsapi_ticket
	 * @return
	 */
	public static JsapiTicket GetJsapiTicket() {
		if (null == JSAPI_TICKET.getTicket() || JSAPI_TICKET.getTicket().equals("") || !checkJsapiTicket()) {
			getAccessToken();//获取access_token
			String requestUrl = JSAPI_TICKET_URl.replace("ACCESS_TOKEN", ACCESS_TOKEN.getToken());
//			String requestUrl = JSAPI_TICKET_URl.replace("ACCESS_TOKEN", "8TTIHzlglSSt_YafG1PE1qTcbhQ0NAJGz6rh_Zz3el3jMXFshalcsarYEQ5O1RvgWnUstQ2dwk7Cuq8wNvBUCD7FIzxZ0362ouqOhqehmwk1lWk9ISyIzeZn1snegKhBJJZaAEAUUK");
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
			// 如果请求成功
			if (null != jsonObject) {
				try {
					JSAPI_TICKET.setTicket(jsonObject.getString("ticket"));
					JSAPI_TICKET.setExpires_in(System.currentTimeMillis());;
				} catch (JSONException e) {
					JSAPI_TICKET.setTicket(null);
					// 获取ticket失败
					System.out.println("获取ticket失败("+jsonObject.getInt("errcode")+")："+WeChatErrorCode.ERRORCODE.get(jsonObject.getInt("errcode")));
				}
			}
		}
		return JSAPI_TICKET;
	}
	
	/*
	 * 检验ticket是否失效
	 */
	public static boolean checkJsapiTicket() {
		return System.currentTimeMillis() - JSAPI_TICKET.getExpires_in() >=7200*1000 ? false:true;
	}
	
	
	// 获取access_token的接口地址（GET） 限200（次/天）

	/**
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;

		String requestUrl = ACCESS_TOKEN_URL.replace("APPID", appid).replace(
				"APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				System.out.println("获取TOKEN失败("+jsonObject.getInt("errcode")+")："+WeChatErrorCode.ERRORCODE.get(jsonObject.getInt("errcode")));
			}
		}
		return accessToken;
	}

	// 菜单创建（POST） 限100（次/天）

	/**
	 * 创建菜单
	 * 
	 * @param menu
	 *            菜单实例
	 * @param accessToken
	 *            有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(String jsonMenu, String accessToken) {
		int result = 0;

		// 拼装创建菜单的url
		String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
		System.out.println(jsonObject.toString());
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				System.out.println("创建菜单失败("+result+")："+WeChatErrorCode.ERRORCODE.get(result));
			}
		}
		return result;
	}

	/**
	 * 创建分组
	 * @param groupJson
	 * @param accessToken
	 * @return
	 */
	public static int createGroup(String groupJson, String accessToken) {

		String url = GROUP_CREATE.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = httpRequest(url, "POST", groupJson);
		int result = 0;
		if (null != jsonObject) {
			if (jsonObject.toString().contains("errcode")) {
				System.out.println("创建菜单失败("+result+")："+WeChatErrorCode.ERRORCODE.get(result));
				return -1;
			}else {
				JSONObject jsonObject2 = (JSONObject) jsonObject.get("group");
				result = Integer.parseInt(jsonObject2.getString("id"));
			}

		}else {
			return -1 ;
		}
		return result;
	}
	
	/**
	 * 移动用户到指定组
	 * @param groupJson
	 * @param accessToken
	 * @param flag   true---用户   flase----医生
	 * @return
	 */
	public static int moveGroup(String openid, Group group) {
 		String groupJson = null;
		if (group == Group.YONGHU) {
			groupJson = "{\"openid\":\""+openid+"\",\"to_groupid\":118}";
		} else {
			groupJson = "{\"openid\":\""+openid+"\",\"to_groupid\":119}";
		}
		//groupJson.replace("openid", openid);
		String accessToken = null;
		if (checkAccessToken()) {
			accessToken = ACCESS_TOKEN.getToken();
		}else {
			ACCESS_TOKEN.setToken(null);
			accessToken = getAccessToken().getToken();
		}
		String url = MOVE_GROUP.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = httpRequest(url, "POST", groupJson);
		int result = 0;
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				System.out.println("分组失败("+result+")："+WeChatErrorCode.ERRORCODE.get(result));
			} else {
				System.out.println("分组成功");
			}
		}
		return result;
	}

	/**
	 * 删除分组
	 * @param groupJson
	 * @param accessToken
	 * @return
	 */
	public static int deleteGroup(String groupJson, String accessToken) {

		String url = DELETE_GROUP.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = httpRequest(url, "POST", groupJson);
		int result = 0;
		try {
			if (null != jsonObject) {
				if (!jsonObject.toString().contains("errcode")) {
					result = 1;
				}else
					result = -1;
			}
			
		} catch (Exception e) {
			result = -1;
			e.printStackTrace();
			return result;
		}
		return result;
	}

	/**
	 * 查询分组情况
	 * @param accessToken
	 * @return
	 */
	public static int queryGroup(String accessToken) {
			
			String url = QUERY_GROUP.replace("ACCESS_TOKEN", accessToken);
			JSONObject jsonObject = httpRequest(url, "GET", null);
			int result = 0;
			if (null != jsonObject) {
				System.out.println(jsonObject.toString());
			}
			return result;
	}

	/**
	 * 测试个性化菜单接口
	 * @param openid
	 */
	public static void testAdditionalMenu(String openid) {
		String accessToken = getAccessToken().getToken();
		String url = TEST_ADDITIONAL_MENU.replace("ACCESS_TOKEN", accessToken);
		
		String json = "{\"user_id\":\""+openid+"\"}";
		JSONObject jsonObject = httpRequest(url, "GET", json);
		int result = 0;
		
	}

	/**
	 * 发送模板消息过程中的设置所属行业
	 * @param openid
	 */
	public static void shezsyhy() {
		String accessToken = getAccessToken().getToken();
		String url = SHEZSSHY.replace("ACCESS_TOKEN", accessToken);
		String json ="{\"industry_id1\":\"2\",\"industry_id2\":\"24\"}";
		JSONObject jsonObject = httpRequest(url, "POST", json);
		int result = 0;
	}

	/**
	 * 发送模板消息过程中的获得模板消息id
	 * @param openid
	 */
	public static String huodmbxxid() {
		String accessToken = getAccessToken().getToken();
		String url = HUODMBXXID .replace("ACCESS_TOKEN", accessToken);
		String json = "{\"template_id_short\":\"OPENTM202312043\"}";
		JSONObject jsonObject = httpRequest(url, "POST", json);
		String templateId = (String) jsonObject.get("template_id");
		return templateId;
	}

	/**
	 * 发送模板消息过程中的获得模板消息id
	 * @param openid
	 */
	public static void fasmbxx(String openid, String templateid) {
		String accessToken = getAccessToken().getToken();
		String url = FASMBXX.replace("ACCESS_TOKEN", accessToken);
		String json = FileUtil.File2String("src/json/test.json");
		json = json.replace("\t", "");
		json = json.replace("\r", "");
		json = json.replace("\n", "");
		json = json.replace("userOPENID", openid);
		json = json.replace("usertemplateid", "3B520i2q1gpPOQ59Pb-rVizq4KaupeLIPv53hoK4yFI");
		JSONObject jsonObject = httpRequest(url, "POST", json);
		int result = 0;
	}
	
	public static int fasptxx(String openid, String context) {
		String accessToken = getAccessToken().getToken();
		String url = FASPTXX.replace("ACCESS_TOKEN", accessToken);
		String path = WeChatTempl.class.getClassLoader().getResource("/").getPath()+"/json/fasptxx.json";
//		String path = "src/fasptxx.json";
		String json = FileUtil.File2String(path);
		json = json.replace("\t", "");
		json = json.replace("\r", "");
		json = json.replace("\n", "");
		json = json.replace("OPENID", openid);
		json = json.replace("sendcontex", context);
		JSONObject jsonObject = httpRequest(url, "POST", json);
		if ("0".equals(jsonObject.get("errcode").toString()) || jsonObject.toString().contains("ok")) {
			return 1;
		}else {
			return 0;
		}
	}
	
	/**创建个性化菜单
	 * @param jsonMenu
	 * @param accessToken
	 * @return
	 */
	public static int createAdditionalMenu(String jsonMenu, String accessToken) {
		int result = 0;
		
		// 拼装创建菜单的url
		String url = ADDITIONALMENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
		System.out.println(jsonObject.toString());
		if (null != jsonObject) {
			if (jsonObject.toString().contains("errcode")) {
				result = -1;
				System.out.println("创建菜单失败("+result+")："+WeChatErrorCode.ERRORCODE.get(result));
			}
		}
		return result;
	}
}


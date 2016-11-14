package com.anteoy.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class SmsUtil {

	public static void main(String[] args) {
		String error = submit("13407171107", "注册动态验证码（786015）。如非本人操作请忽略此短信。【医院快线】");
		System.out.println(error);
	}

	private static final String LINK_USERID = "TJXX002498";
	private static final String LINK_PASSWORD = "123@abc";

	private static final String SITEQI_USERID = "1000";
	private static final String SITEQI_ACCOUNT = "youjiayi";
	private static final String SITEQI_PASSWORD = "dd3ff4";

	private static final Map<Integer, String> LINK_STATUS_MAP = new HashMap<Integer, String>();
	static {
		LINK_STATUS_MAP.put(Integer.valueOf(0), "成功");
		LINK_STATUS_MAP.put(Integer.valueOf(1), "成功");
		LINK_STATUS_MAP.put(Integer.valueOf(-1), "帐号未注册");
		LINK_STATUS_MAP.put(Integer.valueOf(-2), "其他错误");
		LINK_STATUS_MAP.put(Integer.valueOf(-3), "密码错误");
		LINK_STATUS_MAP.put(Integer.valueOf(-4), "手机号格式不对");
		LINK_STATUS_MAP.put(Integer.valueOf(-5), "余额不足");
		LINK_STATUS_MAP.put(Integer.valueOf(-6), "定时发送时间不是有效的时间格式");
		LINK_STATUS_MAP.put(Integer.valueOf(-7), "提交信息末尾未加签名，请添加中文企业签名【 】");
		LINK_STATUS_MAP.put(Integer.valueOf(-8), "发送内容需在1到500个字之间");
		LINK_STATUS_MAP.put(Integer.valueOf(-9), "发送号码为空");
	}

	/**
	 * 发送短信
	 * @param phone
	 * @param content
	 * @return 错误消息
	 */
	public static String submit(String phone, String content) {
		int inputLine = 0;
		try { 
			URL url = null;
			String CorpID = LINK_USERID;
			String Pwd = LINK_PASSWORD;
			String Mobile = phone;
			String Content = URLEncoder.encode(content, "GBK");
			url = new URL("http://mb345.com/WS/BatchSend.aspx?CorpID=" + CorpID + "&Pwd=" + Pwd + "&Mobile=" + Mobile + "&Content=" + Content + "&Cell=&SendTime=");
			BufferedReader in = null;
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			inputLine = new Integer(in.readLine()).intValue();
		} catch (Exception e) {
			System.out.println("网络异常,发送短信失败！");
			inputLine = -2;
		}
		if ((inputLine == 1) || (inputLine == 0)) { // 成功
		} else { // 失败
			String reserve = (String) LINK_STATUS_MAP.get(Integer.valueOf(inputLine));
			reserve = (reserve == null ? String.valueOf(inputLine) : reserve);
			return reserve;
		}
		return null;
	}

	public static String siteqi(String phone, String content) {
		try {
			String param = "action=send&userid=" + SITEQI_USERID + "&account=" + SITEQI_ACCOUNT + "&password=" + SITEQI_PASSWORD + "&mobile=" + phone + "&content=" + content;
			URL url = new URL("http://cs.wmlll.com/sms.aspx");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStream os = connection.getOutputStream();
			os.write(param.getBytes());
			os.flush();
			os.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String text = "";
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				text += inputLine + "\r\n";
			}
//			System.out.println(text);
			return text;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

package com.anteoy.wechat;


import com.anteoy.common.TemplateType;
import com.anteoy.common.ZixunMessage;
import com.anteoy.util.FileUtil;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeChatTempl {
	
	
	
	/* 设置所属行业 */
	private static String SHEZSSHY = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
	
	/* 获得模板消息id */
	private static String HUODMBXXID = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
	
	/* 发送模板消息 */
	private static String FASMBXX = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	/* 发送用户咨询消息模板id */
	private static String ZIXUN_TEMPLATEID = "";
	
	/* 发送医生回复消息模板id */
	private static String HUIF_TEMPLATEID = "JIZzNMxr7PtuZiV1pyy8cIL54NIAVd198kg3EFd6NRU";
	/**
	 * 发送模板消息过程中的设置所属行业
	 * @param openid
	 */
	public static void shezsyhy() {
		String accessToken = WeChatUtil.getAccessToken().getToken();
		String url = SHEZSSHY.replace("ACCESS_TOKEN", accessToken);
		String json ="{\"industry_id1\":\"2\",\"industry_id2\":\"24\"}";
		JSONObject jsonObject = WeChatUtil.httpRequest(url, "POST", json);
		int result = 0;
	}
	
	public static void main(String[] args) {
		ZixunMessage message = new ZixunMessage();
		message.setZixbt("关于小儿骨骼发育问题");
		message.setZixnr("12岁才1.2米");
		message.setZxr("小李");
		message.setTargetOpenid("oqsBawEUBrh5UCBDY-lqLTfeni5s");
		message.setZxsj(new SimpleDateFormat("yyyy-MM-dd hh:MM:ss").format(new Date()));
		message.setMublx(TemplateType.ZIXUNXX);
		fasmbxx(message);
	}

	/**
	 * 发送模板消息过程中的获得回复模板消息id
	 * @param openid
	 */
	public static String huodhfmbxxid() {
		String accessToken = WeChatUtil.getAccessToken().getToken();
		String url = HUODMBXXID .replace("ACCESS_TOKEN", accessToken);
		String json = "{\"template_id_short\":\"OPENTM202312043\"}";
		JSONObject jsonObject = WeChatUtil.httpRequest(url, "POST", json);
		String templateId = (String) jsonObject.get("template_id");
		return templateId;
		
	}

	/**
	 * 发送模板消息过程中的获得咨询模板消息id
	 * @param openid
	 */
	public static String huodzxmbxxid() {
		String accessToken = WeChatUtil.getAccessToken().getToken();
		String url = HUODMBXXID.replace("ACCESS_TOKEN", accessToken);
		String json = "{\"template_id_short\":\"OPENTM400077722\"}";
		JSONObject jsonObject = WeChatUtil.httpRequest(url, "POST", json);
		String templateId = (String) jsonObject.get("template_id");
		System.out.println(jsonObject);
		return templateId;
		
	}
	
	/**
	 * 发送模板消息过程中的获得审核模板消息id
	 * @param openid
	 */
	public static String huodshmbxxid() {
		String accessToken = WeChatUtil.getAccessToken().getToken();
		String url = HUODMBXXID.replace("ACCESS_TOKEN", accessToken);
		String json = "{\"template_id_short\":\"OPENTM207508399\"}";
		JSONObject jsonObject = WeChatUtil.httpRequest(url, "POST", json);
		
		String templateId = (String) jsonObject.get("template_id");
		return templateId;
		
	}

	/**
	 * 发送模板消息过程中的获得模板消息id
	 * flag true 发给医生  false 患者
	 * @param openid
	 */
	public static int fasmbxx(ZixunMessage message) {

		String path = null;
		String accessToken = WeChatUtil.getAccessToken().getToken();
		String url = FASMBXX.replace("ACCESS_TOKEN", accessToken);
		int result = 0;
		if (message.getMublx() == TemplateType.ZIXUNXX) {//发起咨询
			path = WeChatTempl.class.getClassLoader().getResource("/").getPath()+"json/zixmbxx.json";
			String json = FileUtil.File2String(path);
			json = replace(message, json);
			String userTemplateId = "jJFcfLyxC4ZMw-UFumDiUwmqTLI_BYWL78r183rWL2k";
			json = json.replace("usertemplateid", userTemplateId);
			json = json.replace("#{zxr}", message.getZxr());
			json = json.replace("#{zxbt}", message.getZixbt());
			json = json.replace("#{zxnr}", message.getZixnr());
			json = json.replace("#{zxsj}", message.getZxsj());
			result = fasxx(url, "post", json);
		} else if(message.getMublx() == TemplateType.HUIFUXX) {
			path = WeChatTempl.class.getClassLoader().getResource("/").getPath()+"json/huifmbxx.json";
			String json = FileUtil.File2String(path);
			json = replace(message, json);
			String userTemplateId = "3B520i2q1gpPOQ59Pb-rVizq4KaupeLIPv53hoK4yFI";
			json = json.replace("usertemplateid", userTemplateId);
			json = json.replace("#{huifys}", message.getHuifr());
			json = json.replace("#{huifsj}", message.getHuifsj());
			json = json.replace("#{huifnr}", message.getHuifnr());
			result = fasxx(url, "post", json);
		} else if(message.getMublx() == TemplateType.SHENHEXX) {
			path = WeChatTempl.class.getClassLoader().getResource("/").getPath()+"json/shenhjg.json";
			String json = FileUtil.File2String(path);
			json = replace(message, json);
			String userTemplateId = "g5gF2IgUrqNwZZsofwFo3zJbvSME45eSPf5OYe2kj7o";
			json = json.replace("usertemplateid", userTemplateId);
			json = json.replace("#{shenhsj}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			json = json.replace("#{shenhjg}", message.getShjg());
			result = fasxx(url, "post", json);
		}
		return result;
		
	}
	
	
	/**
	 * 将json中的制表符 回车 换行 替换掉，同时将接收者的openid， 咨询id 替换掉
	 * @param message
	 * @param json
	 * @return
	 */
	private static String replace(ZixunMessage message, String json) {
		json = json.replace("\t", "");
		json = json.replace("\r", "");
		json = json.replace("\n", "");
		json = json.replace("userOPENID", message.getTargetOpenid());
		json = json.replace("#{url}", message.getUrl());
		return json;
	}
	
	
	/**
	 * 发送模板消息，获得返回结果，返回0失败，返回1，成功
	 * @param url
	 * @param method
	 * @param json
	 * @return
	 */
	private static int fasxx(String url, String method, String json) {

		int result = 0;
		JSONObject jsonObject = WeChatUtil.httpRequest(url, "POST", json);
		if (jsonObject.get("errcode").toString().equals("0")) {
			result = 1;
		} else {
			result = 0;
		}
		return result;
	}

}

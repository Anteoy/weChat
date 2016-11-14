package com.anteoy.wechat;


import com.anteoy.common.Common;
import com.anteoy.util.FileUtil;
import net.sf.json.JSONObject;

public class WeChatMenu {
	
	public static void main(String[] args) {
		deletemenu();
		chuangjyhcd();
		chuangjyscd();

	}

	/**
	 * 获取所有菜单
	 */
	public static void getmenu() {
		String accessToken = WeChatUtil.getAccessToken().getToken();
		String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN".replaceAll("ACCESS_TOKEN", accessToken);

		JSONObject jsonObject = WeChatUtil.httpRequest(url, "POST", null);
		System.out.println(jsonObject.toString());
	}

	/**
	 * 删除所有菜单
	 */
	public static void deletemenu() {
		String accessToken = WeChatUtil.getAccessToken().getToken();
		String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN".replaceAll("ACCESS_TOKEN", accessToken);

		JSONObject jsonObject = WeChatUtil.httpRequest(url, "POST", null);
		System.out.println(jsonObject.toString());
	}

	/**
	 * 创建用户菜单
	 */
	public static void chuangjyhcd() {
		
//		String path = WeChatTempl.class.getClassLoader().getResource("/").getPath()+"chuangjyhcd.json"; //web项目下运行的路径
		String path = "src/json/chuangjyhcd.json"; // java application文件 路径
		String json = getJson(path);
		String accessToken = WeChatUtil.getAccessToken().getToken();
		System.out.println("获取到的TOKEN：\n"+accessToken);
		// 调用接口创建菜单
		int result = WeChatUtil.createMenu(json,accessToken);
		// 判断菜单创建结果
		if (0 == result)
			System.out.println("菜单创建成功");
		else
			System.out.println("菜单创建失败");
	}

	/**
	 * 创建医生个性化菜单
	 */
	public static void chuangjyscd() {

//		String path = WeChatTempl.class.getClassLoader().getResource("/").getPath()+"chuangjcd.json";
		String path = "src/json/chuangjyscd.json"; // java application文件 路径
		String json = getJson(path);
		String accessToken = WeChatUtil.getAccessToken().getToken();
		System.out.println("获取到的TOKEN：\n"+accessToken);
		// 调用接口创建菜单
		int result = WeChatUtil.createAdditionalMenu(json,accessToken);
		// 判断菜单创建结果
		if (0 == result)
			System.out.println("菜单创建成功");
		else
			System.out.println("菜单创建失败");
	}

	public static String getJson(String path) {
		String json = FileUtil.File2String(path);
		json = json.replace("\t", "");
		json = json.replace("\r", "");
		json = json.replace("\n", "");
		json = json.replace("{Common.URL}", "\""+ Common.URL);
		return json;
	}

	

}

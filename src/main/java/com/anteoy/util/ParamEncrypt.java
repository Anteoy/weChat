package com.anteoy.util;

public class ParamEncrypt {

	public static void main(String[] args) {
		String code = code("yisbh");
		System.out.println(code);
		String key = decode(code);
		System.out.println(key);
	}

	public static String code(String key) {
		int _day = (int) (System.currentTimeMillis() / 1000 / 3600 / 24);
		String day = String.valueOf(_day);
		day = day + day;
//		System.out.println(day);

		key = "          ".substring(key.length()) + key;
//		System.out.println("\"" + key + "\"");
		
		String hexs = "";
		for (int i=0; i<key.length(); i++) {
			char kc = key.charAt(i);
			char dc = day.charAt(i);
			int t = kc*dc;
			String hex = Integer.toHexString(t);
			hex = "0000".substring(hex.length()) + hex;
//			System.out.println(kc+"*"+dc+"="+t+"="+hex);
			hexs += hex;
		}
//		System.out.println(hexs);
		return hexs;
	}

	public static String decode(String value) {
		int _day = (int) (System.currentTimeMillis() / 1000 / 3600 / 24);
		String day = String.valueOf(_day);
		day = day + day;
//		System.out.println(day);
		
		int len = value.length() / 4;
		int[] hexs = new int[len];
		for (int i = 0; i < len; i++) {
			String h = value.substring(i * 4, (i + 1) * 4);
			hexs[i] = Integer.valueOf(h, 16);
//			System.out.println(h + "=" + hexs[i]);
		}
		
		String key = "";
		for (int i = 0; i < hexs.length; i++) {
			char dc = day.charAt(i);
			char kc = (char) (hexs[i] / dc);
			key += kc;
		}
//		System.out.println(key);
		return key.trim();
	}

}

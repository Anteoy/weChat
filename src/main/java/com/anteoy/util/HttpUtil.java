/*
package com.anteoy.util;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

*/
/**
 * HTTP控制类
 * 
 * @author
 *
 *//*

public class HttpUtil {

	public static void main(String[] args) {
	}

	public static Map<String, Object> GetJsonMap(String href) {
		return GetJsonMap(href, null);
	}

	public static Map<String, Object> GetJsonMap(String href, Map<String, String> params) {
		try {
			String text = Post(href, params);
			System.out.println(text);
			JSONObject jsonObject = JSONObject.fromObject(text);
			Iterator<?> it = jsonObject.keys();

			Map<String, Object> map = new HashMap<String, Object>();
			while (it.hasNext()) {
				String key = String.valueOf(it.next());
				Object value = jsonObject.get(key);
				map.put(key, value);
			}
			return map;

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String Post(String url, Map<String, String > params) throws HttpException, IOException {
		return Post(url, params, "utf-8", false);
	}

	public static String Post(String url, Map<String, String > params, String charset) throws HttpException, IOException {
		return Post(url, params, charset, false);
	}

	public static String Post(String url, Map<String, String> params, String charset, boolean pretty) throws HttpException, IOException {
		StringBuffer response = new StringBuffer();
		HttpClient client = new HttpClient();
		HttpMethod method = new PostMethod(url);
//		if (params != null) {
//			HttpMethodParams p = new HttpMethodParams();
//			for (Map.Entry<String, String> entry : params.entrySet()) {
//				p.setParameter(entry.getKey(), entry.getValue());
//			}
//			method.setParams(p);
//		}

		if (params != null) {
			Set<?> set = params.entrySet();
			int size = set.size();
			NameValuePair[] data = new NameValuePair[size];
			int index = 0;
			for (Map.Entry<String, String> entry : params.entrySet()) {
				data[index++] = new NameValuePair(entry.getKey(), entry.getValue());
			}
			method.setQueryString(data);
		}
		
		try {
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));
				String line;
				while ((line = reader.readLine()) != null) {
					if (pretty)
						response.append(line).append(System.getProperty("line.separator"));
					else
						response.append(line);
				}
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			method.releaseConnection();
		}
		return response.toString();
	}

}
*/

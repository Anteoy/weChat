/*
package com.anteoy.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class ParseXml {

	*/
/**
	 * ����΢�ŷ���������XML��
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 *//*

	@SuppressWarnings("unchecked")
	public static HashMap<String, String> parseXml(HttpServletRequest request) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();

		InputStream inputStream = request.getInputStream();

		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);

		Element root = document.getRootElement();

		List<Element> elementList = root.elements();

		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		inputStream.close();
		inputStream = null;

		return map;
	}

}
*/

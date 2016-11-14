package com.anteoy.util;

import java.io.File;
import java.io.FileInputStream;

public class FileUtil {

	public static String File2String(String file) {
		return File2String(new File(file), "utf-8");
	}

	public static String File2String(File file, String charset) {
		String result = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			int len = (int) file.length();
			byte[] b = new byte[len];
			fis.read(b, 0, len);
			fis.close();
			result = new String(b, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}

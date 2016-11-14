/**
 * @filename Uncompress.java
 * @package recv[Gucang]
 * @description 压缩解压工具类
 * @author
 * @date 2012-8-12 下午3:42:29
 * @version v0.1
 */
package com.anteoy.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * GZIP压缩工具类
 * @author
 */
public class GZipCompress {

	/**
	 * 压缩数据
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static byte[] compress(byte[] data) {
		return compress(data, 0, data.length);
	}

	/**
	 * 压缩数据
	 * @param data
	 * @param start
	 * @return
	 * @throws IOException
	 */
	public static byte[] compress(byte[] data, int start) {
		return compress(data, start, data.length - start);
	}

	/**
	 * 压缩数据
	 * @param data
	 * @param start
	 * @param length
	 * @return
	 * @throws IOException
	 */
	public static byte[] compress(byte[] data, int start, int length) {
		data = ArrayUtil.subArray(data, start, length);
		ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
		GZIPOutputStream os;
		try {
			os = new GZIPOutputStream(bos);
			os.write(data);
			os.finish();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
	}

	/**
	 * 解压数据
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static byte[] decompress(byte[] data) {
		return decompress(data, 0, data.length);
	}

	/**
	 * 解压数据
	 * @param data
	 * @param start
	 * @return
	 * @throws IOException
	 */
	public static byte[] decompress(byte[] data, int start) {
		return decompress(data, start, data.length - start);
	}

	/**
	 * 解压数据
	 * @param data
	 * @param start
	 * @param length
	 * @return
	 * @throws IOException
	 */
	public static byte[] decompress(byte[] data, int start, int length) {
		try {
			GZIPInputStream gzis = new GZIPInputStream(new ByteArrayInputStream(data, start, length));
			ByteArrayOutputStream bos = new ByteArrayOutputStream(length);
			byte[] buff = null;
			byte[] temp = new byte[10240];
			int readCnt = 0;
			while ((readCnt = gzis.read(temp)) > -1) {
				bos.write(temp, 0, readCnt);
			}
			gzis.close();
			buff = bos.toByteArray();
			bos.close();
			System.gc();
			return buff;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

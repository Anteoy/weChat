/**
 * @filename GeneralUitl.java
 * @package
 * @description 常用工具方法
 * @author
 * @date 2012-6-10 上午12:32:46
 * @version v0.1
 *//*

package com.anteoy.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

*/
/**
 * 常用方法工具
 * @author
 *//*

public class GeneralUitl {

	public static JsonConfig JSON_CONFIG = new JsonConfig();
	static {
		JSON_CONFIG.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessor() {
			private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			@Override
			public Object processObjectValue(String key, Object value, JsonConfig config) {
				return process(value);
			}

			@Override
			public Object processArrayValue(Object value, JsonConfig config) {
				return process(value);
			}

			private Object process(Object value) {
				if (value instanceof java.util.Date) {
					return sdf.format(value);
				}
				return value == null ? null : value.toString();
			}
		});
	}

	private GeneralUitl() {
	}

	*/
/**
	 * 组装树形结构
	 * @param list 要拼装的List
	 * @param id ID字段
	 * @param parent 父节点ID字段
	 * @param children 子节点字段
	 * @return
	 *//*

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> List<T> Tree(List<T> list, String id, String parent, String children) {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < list.size(); i++) {
			T t = list.get(i);
			boolean map = t instanceof Map;
			try {
				T _parent = null;
				Object _pid = map ? ((Map) t).get(parent) : ClassUtil.GetFieldValue(t, parent);
				if (_pid != null) {
					for (int j = 0; j < list.size(); j++) {
						T _t = list.get(j);
						Object _id = map ? ((Map) _t).get(id) : ClassUtil.GetFieldValue(_t, id);
						if (_pid.equals(_id)) {
							_parent = _t;
							break;
						}
					}
				}
				if (_parent != null) {
					List<T> _children = (List<T>) (map ? ((Map) _parent).get(children) : ClassUtil.GetFieldValue(_parent, children));
					if (_children == null) {
						_children = new ArrayList<T>();
						_children.add(t);
						if (map) {
							((Map) _parent).put(children, _children);
						} else {
							ClassUtil.SetFieldValue(_parent, children, _children);
						}
					} else {
						_children.add(t);
					}
				} else {
					result.add(t);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return result;
	}

	*/
/**
	 * 组装成Easyui的树形结构
	 * @param list 要拼装的List
	 * @param id ID字段
	 * @param text 显示文字字段
	 * @param parent 父节点ID字段
	 * @return
	 *//*

	@SuppressWarnings({ "unchecked" })
	public static <T> List<Map<String, Object>> EasyTree(List<T> list, String id, String text, String parent) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> srcList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			srcList.add(ClassUtil.ToMap(list.get(i)));
		}
		for (int i = 0; i < srcList.size(); i++) {
			Map<String, Object> t = srcList.get(i);
			t.put("id", t.get(id));
			t.put("text", t.get(text));
			try {
				Map<String, Object> _parent = null;
				Object _pid = t.get(parent);
				if (_pid != null) {
					for (int j = 0; j < srcList.size(); j++) {
						Map<String, Object> _t = srcList.get(j);
						Object _id = _t.get(id);
						if (_pid.equals(_id)) {
							_parent = _t;
							break;
						}
					}
				}
				if (_parent != null) {
					List<Map<String, Object>> _children = (List<Map<String, Object>>) _parent.get("children");
					if (_children == null) {
						_children = new ArrayList<Map<String, Object>>();
						_children.add(t);
						_parent.put("children", _children);
					} else {
						_children.add(t);
					}
				} else {
					result.add(t);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return result;
	}

	*/
/**
	 * 组装成Easyui的树形结构
	 * @param list 要拼装的List
	 * @param id ID字段
	 * @param text 显示文字字段
	 * @param parent 父节点ID字段
	 * @return
	 *//*

	@SuppressWarnings({ "unchecked" })
	public static <T> List<Map<String, Object>> ExtTree(List<T> list, String idColumn, String textColumn, String parentColumn, String iconClsColumn) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> srcList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < list.size(); i++) {
			srcList.add(ClassUtil.ToMap(list.get(i)));
		}
		for (int i = 0; i < srcList.size(); i++) {
			Map<String, Object> t = srcList.get(i);
			t.put("id", t.get(idColumn));
			t.put("text", t.get(textColumn));
			if(iconClsColumn != null && iconClsColumn.length() > 0) t.put("iconCls", t.get(iconClsColumn));
			t.put("leaf", t.get("leaf") == null ? true : t.get("leaf"));
			try {
				Map<String, Object> _parent = null;
				Object _pid = t.get(parentColumn);
				if (_pid != null) {
					for (int j = 0; j < srcList.size(); j++) {
						Map<String, Object> _t = srcList.get(j);
						Object _id = _t.get(idColumn);
						if (_pid.equals(_id)) {
							_parent = _t;
							break;
						}
					}
				}
				if (_parent != null) {
					List<Map<String, Object>> _children = (List<Map<String, Object>>) _parent.get("children");
					if (_children == null) {
						_children = new ArrayList<Map<String, Object>>();
						_children.add(t);
						_parent.put("children", _children);
					} else {
						_children.add(t);
					}
					_parent.put("leaf", false);
				} else {
					result.add(t);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return result;
	}

	*/
/**
	 * 将16进制字符串转换为字节数据
	 * @param data
	 * @return
	 *//*

	public static byte[] Hex2Byte(String data) {
		String[] datas = data.trim().replaceAll("0x", "").split("\\s+");
		byte[] result = new byte[datas.length];
		for (int i = 0; i < datas.length; i++) {
			String d = datas[i];
			result[i] = (byte) (Character.digit(d.charAt(0), 16) << 4 | Character.digit(d.charAt(1), 16));
		}
		return result;
	}

	*/
/**
	 * 将字节数据转换为16进制字符串
	 * @param data
	 * @return
	 *//*

	public static String Byte2Hex(byte[] data) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			String hex = Integer.toHexString(data[i] & 0xFF);
			result.append((hex.length() == 1 ? "0" : "")).append(hex);
		}
		return result.toString();
	}

	*/
/**
	 * MD5加密
	 * @param origin
	 * @return
	 *//*

	public static byte[] encodeMD5(byte[] origin) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return md.digest(origin);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}


	*/
/**
	 * 根据JSON的list生成字段
	 * @param data
	 * @return
	 *//*

	public static List<String> CreateFields(List<?> data) {
		List<String> keys = new ArrayList<String>();
		for (int i = 0; i < data.size(); i++) {
			Object d = data.get(i);
			Map<String, Object> map = ClassUtil.ToMap(d);
			Set<String> _keys = map.keySet();
			for (String key : _keys) {
				boolean found = false;
				for (int j = 0; j < keys.size(); j++) {
					if (key.equals(keys.get(j))) {
						found = true;
						break;
					}
				}
				if (!found) {
					keys.add(key);
				}
			}
			if (!(d instanceof Map)) break;
		}
		return keys;
	}
	
}
*/

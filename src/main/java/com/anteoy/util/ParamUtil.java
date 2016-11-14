/**
 * @filename DateUtil.java
 * @description 参数控制工具类
 * @author
 * @date 2012-7-23 下午21:53:32
 * @version v0.1
 */
package com.anteoy.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 参数控制工具类,可对Request,Seesion,ResultSet,Map对象进行操作
 * @author
 */
public class ParamUtil {

	private ParamUtil() {
	}

	public static boolean isEmpty(Object object) {
		if (object == null) return true;
		if (object instanceof String && ((String) object).length() == 0) return true;
		if (object instanceof List && ((List<?>) object).size() == 0) return true;
		return false;
	}

	/**
	 * 取得并返回request参数值
	 *
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static String getString(HttpServletRequest request, String paramName) {
		String param = request.getParameter(paramName);
		if (param != null) {
			return param;
		}
		String attr = (String) request.getAttribute(paramName);
		if (attr != null) {
			return attr;
		}
		return null;
	}

	/**
	 * 取得并返回request参数值,如果返回空值则用defaultString代替
	 *
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static String getString(HttpServletRequest request, String paramName, String defaultString) {
		String temp = getString(request, paramName);
		return temp == null ? defaultString : temp;
	}

	/**
	 * 取得并返回request参数值
	 *
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static String[] getStrings(HttpServletRequest request, String paramName) {
		String[] params = request.getParameterValues(paramName);
		if (params != null) {
			return params;
		}
		
		Object attr = request.getAttribute(paramName);
		if (attr != null) {
			if (attr instanceof String) {
				params = new String[] {(String) attr};
			} else if (attr.getClass().isArray()) {
				Object[] ao = (Object[]) attr;
				params = new String[ao.length];
				for (int i = 0; i < ao.length; i++) {
					params[i] = String.valueOf(ao[i]);
				}
			}
			return params;
		}
		return null;
	}

	/**
	 * 取得并返回request参数值,已转换为整型数
	 *
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static Integer getInt(HttpServletRequest request, String paramName) {
		try {
			String temp = getString(request, paramName);
			return temp == null ? null : Integer.parseInt(temp);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得并返回request参数值,已转换为整型数，如果返回空值则用defaultInt代替
	 *
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static Integer getInt(HttpServletRequest request, String paramName, int defaultInt) {
		Integer temp = getInt(request, paramName);
		return temp == null ? defaultInt : temp;
	}

	/**
	 * 取得并返回request参数值,已转换为整型数
	 *
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static Integer[] getInts(HttpServletRequest request, String paramName) {
		try {
			String[] temp = getStrings(request, paramName);
			Integer[] result = new Integer[temp.length];
			for (int i = 0; i < result.length; i++) {
				String tmp = temp[i];
				result[i] = tmp == null ? null : Integer.parseInt(tmp);
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得并返回request参数值,已转换为浮点数
	 *
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static Float getFloat(HttpServletRequest request, String paramName) {
		try {
			String temp = getString(request, paramName);
			return temp == null ? null : Float.parseFloat(temp);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得并返回request参数值,已转换为浮点数，如果返回空值则用defaultInt代替
	 *
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static Float getFloat(HttpServletRequest request, String paramName, float defaultInt) {
		Float temp = getFloat(request, paramName);
		return temp == null ? defaultInt : temp;
	}

	/**
	 * 取得并返回request参数值,已转换为DOUBLE
	 *
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static Double getDouble(HttpServletRequest request, String paramName) {
		try {
			String temp = getString(request, paramName);
			return temp == null ? null : Double.parseDouble(temp);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得并返回request参数值,已转换为浮点数，如果返回空值则用defaultInt代替
	 *
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static Double getDouble(HttpServletRequest request, String paramName, double defaultDouble) {
		Double temp = getDouble(request, paramName);
		return temp == null ? defaultDouble : temp;
	}

	/**
	 * 取得并返回request参数值,已转换为长整型数，如果返回空值则用defaultLong代替
	 *
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static Long getLong(HttpServletRequest request, String paramName) {
		String temp = null;
		try {
			temp = getString(request, paramName);
		} catch (Exception e) {
			;
		}
		return temp == null ? null : Long.parseLong(temp.trim());
	}

	/**
	 * 取得并返回request参数值,已转换为长整型数，如果返回空值则用defaultLong代替
	 *
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static Long getLong(HttpServletRequest request, String paramName, long defaultLong) {
		Long temp = getLong(request, paramName);
		return temp == null ? defaultLong : temp;
	}

	/**
	 * 取得Boolean
	 *
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static Boolean getBoolean(HttpServletRequest request, String paramName) {
		return "true".equals(ParamUtil.getString(request, paramName, "false").toLowerCase()) ? true : false;
	}

	/**
	 * 取得并返回request参数值,已转换为DOUBLE
	 *
	 * @param request
	 * @param paramName
	 * @return
	 * @throws ParseException
	 */
	public static java.util.Date getDate(HttpServletRequest request, String paramName) {
		String temp = getString(request, paramName);
		if (temp == null || temp.trim().length() == 0) {
			return null;
		} else {
			Date result = string2date(temp);
			return result;
		}
	}

	/**
	 * 取得并返回request参数值,已转换为浮点数，如果返回空值则用defaultInt代替
	 *
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static java.util.Date getDate(HttpServletRequest request, String paramName, java.util.Date date) {
		Date temp = getDate(request, paramName);
		return temp == null ? date : temp;
	}

	/**
	 * 取得并返回request参数值
	 *
	 * @param request
	 * @param paramName
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Date getSqlDate(HttpServletRequest request, String paramName) {
		String temp = getString(request, paramName);
		if (temp == null || temp.trim().length() == 0) {
			return null;
		} else {
			Date result = string2date(temp);
			java.sql.Date sqldata = new java.sql.Date(result.getTime());
			return sqldata;
		}
	}

	/**
	 * 取得并返回request参数值
	 *
	 * @param request
	 * @param paramName
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Timestamp getTimestamp(HttpServletRequest request, String paramName) {
		String temp = getString(request, paramName);
		if (temp == null || temp.trim().length() == 0) {
			return null;
		} else {
			Date result = string2date(temp);
			java.sql.Timestamp sqldata = new java.sql.Timestamp(result.getTime());
			return sqldata;
		}
	}

	/**
	 * 取得并返回request参数值
	 *
	 * @param request
	 * @param paramName
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Time getTime(HttpServletRequest request, String paramName) {
		String temp = getString(request, paramName);
		if (temp == null || temp.trim().length() == 0) {
			return null;
		} else {
			java.sql.Time time = string2time(temp);
			return time;
		}
	}

	/**
	 * 取得并返回rs指定位置的字段值
	 *
	 * @param rs
	 * @param i
	 * @return
	 */
	public static String getString(ResultSet rs, int i) {
		return getString(rs, i, null);
	}

	/**
	 * 取得并返回rs指定列名的字段值
	 *
	 * @param rs
	 * @param columnName
	 * @return
	 */
	public static String getString(ResultSet rs, String columnName) {
		return getString(rs, columnName, null);
	}

	/**
	 * 取得并返回rs指定列名的字段值
	 *
	 * @param rs
	 * @param columnName
	 * @return
	 */
	public static String getString(ResultSet rs, String columnName, String defValue) {
		try {
			String s = rs.getString(columnName);
			return s == null ? defValue : s;
		} catch (Exception e) {
			return defValue;
		}
	}

	/**
	 * 取得并返回rs指定列的字段值
	 *
	 * @param rs
	 * @param i
	 * @return
	 */
	public static String getString(ResultSet rs, int i, String defValue) {
		try {
			String s = rs.getString(i);
			return s == null ? defValue : s;
		} catch (Exception e) {
			return defValue;
		}
	}

	/**
	 * 取得并返回rs指定位置的字段值，返回整数
	 *
	 * @param rs
	 * @param i
	 * @return
	 */
	public static int getInt(ResultSet rs, int i) {
		try {
			return rs.getInt(i);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得并返回rs指定位置的字段值，返回整数
	 *
	 * @param rs
	 * @param columnName
	 * @return
	 */
	public static int getInt(ResultSet rs, String columnName) {
		try {
			return rs.getInt(columnName);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得并返回rs参数值,返回浮点数
	 *
	 * @param rs
	 * @param i
	 * @return
	 */
	public static float getFloat(ResultSet rs, int i) {
		try {
			return rs.getFloat(i);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得并返回rs指定位置的字段值，返回浮点数
	 *
	 * @param rs
	 * @param columnName
	 * @return
	 */
	public static float getFloat(ResultSet rs, String columnName) {
		try {
			return rs.getFloat(columnName);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 取得并返回rs指定位置的字段值，将日期转换为字符串
	 *
	 * @param rs
	 * @param i
	 * @return
	 */
	public static String getDateString(ResultSet rs, int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = getDate(rs, i);
		return sdf.format(date);
	}

	/**
	 * 取得并返回rs指定位置的字段值，将日期转换为字符串
	 *
	 * @param rs
	 * @param i
	 * @return
	 */
	public static String getDateString(ResultSet rs, String columnName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = new Date(rs.getTimestamp(columnName).getTime());
			return sdf.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 转换成java.util.date
	 *
	 * @param rs
	 * @param i
	 * @return
	 */
	public static Date getDate(ResultSet rs, int i) {
		try {
			return new Date(rs.getTimestamp(i).getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得并返回rs指定位置的字段值，将时间转换为字符串
	 *
	 * @param rs
	 * @param i
	 * @return
	 */
	public static String getTimeString(ResultSet rs, int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			Date date = new Date(rs.getTimestamp(i).getTime());
			return sdf.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 转换成java.util.date
	 *
	 * @param rs
	 * @param i
	 * @return
	 */
	public static Date getTime(ResultSet rs, int i) {
		try {
			return new Date(rs.getTimestamp(i).getTime());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得并返回rs指定位置的字段值，将日期时间转换为字符串
	 *
	 * @param rs
	 * @param i
	 * @return
	 */
	public static String getDateTime(ResultSet rs, int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = new Date(rs.getTimestamp(i).getTime());
			return sdf.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 取得格式化后的值，形式如:2002-9-9 12:55:21
	 *
	 * @param rs
	 * @param columnName
	 * @return
	 */
	public static String getDateTime(ResultSet rs, String columnName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = new Date(rs.getTimestamp(columnName).getTime());
			return sdf.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 格式化Session,根据Session名称返回格式化后的值
	 *
	 * @param session
	 * @param sessionName
	 * @return
	 */
	public static String getString(HttpSession session, String sessionName) {
		String sessionTemp = (String) session.getAttribute(sessionName);
		if (sessionTemp == null)
			sessionTemp = "";
		return sessionTemp;
	}

	/**
	 * 从map中取得String
	 *
	 * @param m
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static String getString(Map<String, Object> m, String name, String defaultValue) {
		String temp = String.valueOf(m.get(name));
		if (temp == null)
			temp = defaultValue;
		return temp;
	}

	/**
	 * 从map中取得String
	 *
	 * @param m
	 * @param name
	 * @return
	 */
	public static String getString(Map<String, Object> m, String name) {
		return getString(m, name, null);
	}

	/**
	 * 从Map中取得int
	 *
	 * @param m
	 * @param name
	 * @param defaultInt
	 * @return
	 */
	public static int getInt(Map<String, Object> m, String name, int defaultInt) {
		try {
			String temp = getString(m, name);
			return temp == null ? defaultInt : Integer.parseInt(temp.trim());
		} catch (Exception e) {
			return defaultInt;
		}
	}

	/**
	 * 取得日期
	 *
	 * @param m
	 * @param name
	 * @param defaultDate
	 * @return
	 */
	public static Date getDate(Map<String, Object> m, String name, Date defaultDate) {
		return null;
	}

	/**
	 * 从Map中取得int
	 *
	 * @param m
	 * @param name
	 * @return
	 */
	public static int getInt(Map<String, Object> m, String name) {
		return getInt(m, name, 0);
	}

	/**
	 * 从request中填充内容至对像
	 * 要求：
	 * 1.网页上的对像名必须和对像中字段名一致才能填充
	 * 2.对像名要么全大写，要么全小写。
	 * 3 出错返回空对像
	 * 4 名字不一致将不能填充
	 *
	 * @param c
	 * @param request
	 * @return
	 */
	public static <T> T getObject(Class<T> c, HttpServletRequest request) {
		T instance = null;
		try {
			final Method[] m = c.getDeclaredMethods();
			if (m.length <= 0)
				return null;
			instance = c.newInstance();
			for (int i = 0; i < m.length; i++) {
				if (m[i].getName().indexOf("set") != -1) {
					String name = m[i].getName();
					String paramname = name.substring(3, name.length()).toLowerCase();
					Class<?>[] param = m[i].getParameterTypes();
					Object[] paramValue = new Object[param.length];
					if (param.length > 0) {
						for (int tt = 0; tt < param.length; tt++) {
							Class<?> type = param[tt];
							paramValue[tt] = getObjectWithTypeName(type, request, paramname);
						}
						m[i].invoke(instance, paramValue);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}

	/**
	 * 根据前缀, 获取对象列表
	 * @param clazz
	 * @param request
	 * @param suffix
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> buildObjects(Class<T> clazz, HttpServletRequest request, String suffix) {

		List<Object[]> objs = new ArrayList<Object[]>();

		Enumeration<?> enums = request.getParameterNames();
		while (enums.hasMoreElements()) {
			String name = (String) enums.nextElement();
			Object value = request.getParameter(name);
			if (value == null || "".equals(value)) continue;
			if (name.startsWith(suffix)) {
				String[] names = name.split("\\."); // 把前台传来的参数名称拆分成[对象名.属性名]
				if (names.length == 2) {
					Object obj = null;
					for (int i = 0; i < objs.size(); i++) {
						if (names[0].equals(objs.get(i)[0])) {
							obj = (T) objs.get(i)[1];
						}
					}
					if (obj == null) {
						try {
							obj = clazz.newInstance();
							objs.add(new Object[] {names[0], obj});
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					try {
						String field= names[1];
						Class<?> type = ClassUtil.GetFieldType(obj, field);
						value = getObjectWithTypeName(type, request, field);
						ClassUtil.SetFieldValue(obj, field, value);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		List<T> ts = new ArrayList<T>();
		for (int i = 0; i < objs.size(); i++) {
			ts.add((T) objs.get(i)[1]);
		}
		return ts;
	}

	/**
	 * 供 getObject和buildObjects使用
	 * @param type
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T> T getObjectWithTypeName(Class<T> type, HttpServletRequest request, String name) {
		T t = null;
		if (type == java.lang.String.class) {
			t = (T) getString(request, name);
		} else if (type == int.class || type == java.lang.Integer.class) {
			t = (T) getInt(request, name);
		} else if (type == long.class || type == java.lang.Long.class) {
			t = (T) getInt(request, name);
		} else if (type == double.class || type == java.lang.Double.class) {
			t = (T) getDouble(request, name);
		} else if (type == float.class || type == java.lang.Float.class) {
			t = (T) getFloat(request, name);
		} else if (type == java.util.Date.class) {
			t = (T) getDate(request, name);
		} else if (type == java.sql.Date.class) {
			t = (T) getSqlDate(request, name);
		} else if (type == java.sql.Timestamp.class) {
			t = (T) getTimestamp(request, name);
		} else if (type == java.sql.Time.class) {
			t = (T) getTime(request, name);
		}
		return t;
	}

	private static java.sql.Time string2time(String temp) {
		java.sql.Time time = null;
		if (temp == null || temp.length() == 0) return null;
		String[] tmps = temp.split(":");
		int h = Integer.parseInt(tmps[0]);
		int m = Integer.parseInt(tmps[1]);
		int s = tmps.length > 2 ? Integer.parseInt(tmps[2]) : 0;
		long t = (h*60*60*1000) + (m*60*1000) + (s*1000);
		time = new java.sql.Time(t);
		return time;
	}

	private static Date string2date(String temp) {

		Date result = null;
		try {
			SimpleDateFormat formatter = null;
			if (temp.indexOf(" ") > -1) {
				if (temp.indexOf("CST") > -1) {
					formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy", Locale.ENGLISH);
				} else if (temp.indexOf("CDT") > -1) {
					formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CDT' yyyy", Locale.ENGLISH);
				} else {
					String[] aa = temp.split(":");
					if (aa.length == 3)
						formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					else if (aa.length == 2)
						formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					else
						formatter = new SimpleDateFormat("yyyy-MM-dd HH");
				}
			} else if (temp.indexOf("年") != -1) {
				formatter = new SimpleDateFormat("yyyy年MM月dd日");
			} else {
				formatter = new SimpleDateFormat("yyyy-MM-dd");
			}
			result = formatter.parse(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	
	}
}

package cn.com.hxfz.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * 
 * @author ThinkGem
 * @version 2013-05-22
 */
public class StringUtils {
	 /** The Constant logger. */
    private final static Logger logger = (Logger) Logger.getLogger(StringUtils.class);
    
	/**
	 * Is not empty.
	 *
	 * @param s the s
	 * @return true, if is not empty
	 */
	public static boolean isNotEmpty(String s) {
		if (s == null || "".equals(s) || "undefined".equals(s)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 缩略字符串（不区分中英文字符）.
	 *
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return the string
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : str.toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			logger.error(e);
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 缩略字符串（替换html）.
	 *
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return the string
	 */
	public static String rabbr(String str, int length) {
		return abbr(str, length);
	}

	/**
	 * 转换为Double类型.
	 *
	 * @param val the val
	 * @return the double
	 */
	public static Double toDouble(Object val) {
		if (val == null) {
			return 0D;
		}
		try {
			return Double.valueOf(val.toString());
		} catch (Exception e) {
			logger.error(e);
			return 0D;
		}
	}

	/**
	 * 转换为Float类型.
	 *
	 * @param val the val
	 * @return the float
	 */
	public static Float toFloat(Object val) {
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型.
	 *
	 * @param val the val
	 * @return the long
	 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型.
	 *
	 * @param val the val
	 * @return the integer
	 */
	public static Integer toInteger(Object val) {
		return toLong(val).intValue();
	}

	/**
	 * Get random string.
	 *
	 * @param length the length
	 * @return the random string
	 */
	public static String getRandomString(int length) {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; i++) {

			int number = random.nextInt(str.length() - 1);

			sb.append(str.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * Get random upper string.
	 *
	 * @param length the length
	 * @return the random upper string
	 */
	public static String getRandomUpperString(int length) {
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		Random random = new Random();

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < length; i++) {

			int number = random.nextInt(str.length() - 1);

			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	// 首字母大写
	/**
	 * Capture name.
	 *
	 * @param name the name
	 * @return the string
	 */
	public static String captureName(String name) {
		// name = name.substring(0, 1).toUpperCase() + name.substring(1);
		// return name;
		char[] cs = name.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);

	}
	
	/**
	 * Get encoding.
	 *
	 * @param str the str
	 * @return the encoding
	 */
	public static String getEncoding(String str) {      
	       String encode = "GB2312";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s = encode;      
	              return s;      
	           }      
	       } catch (Exception exception) {
	    	   logger.error(exception);
	       }      
	       encode = "ISO-8859-1";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s1 = encode;      
	              return s1;      
	           }      
	       } catch (Exception exception1) {  
	    	   logger.error(exception1);
	       }      
	       encode = "UTF-8";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s2 = encode;      
	              return s2;      
	           }      
	       } catch (Exception exception2) { 
	    	   logger.error(exception2);
	       }      
	       encode = "GBK";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s3 = encode;      
	              return s3;      
	           }      
	       } catch (Exception exception3) {   
	    	   logger.error(exception3);
	       }      
	      return "";      
	   }     
	
}

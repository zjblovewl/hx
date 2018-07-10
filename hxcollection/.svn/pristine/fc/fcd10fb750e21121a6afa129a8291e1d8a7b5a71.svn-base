package cn.jpush.api.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;

import org.apache.log4j.Logger;


// TODO: Auto-generated Javadoc
/**
 * The Class StringUtils.
 */
public class StringUtils {
	
	/** The Constant logger. */
	private static final Logger logger = (Logger) Logger.getLogger(StringUtils.class);
	
	/** The Constant hexDigits. */
	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

	/**
	 * Byte array to hex string.
	 *
	 * @param b the b
	 * @return the string
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * Byte to hex string.
	 *
	 * @param b the b
	 * @return the string
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * To m d5.
	 *
	 * @param origin the origin
	 * @return the string
	 */
	public static String toMD5(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		    logger.debug(ex);
			ex.printStackTrace();
		}
		return resultString;
	}
	
	/**
	 * Encode param.
	 *
	 * @param param the param
	 * @return the string
	 */
	public static String encodeParam(String param) {
		String encodeParam = null;
		try {
			encodeParam = URLEncoder.encode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		    logger.debug(e);
			e.printStackTrace();
		}
		return encodeParam;
	}
	
    /**
     * Array to string.
     *
     * @param values the values
     * @return the string
     */
    public static String arrayToString(String[] values) {
        if (null == values) return "";
        
        StringBuffer buffer = new StringBuffer(values.length);
        for (int i = 0; i < values.length; i++) {
            buffer.append(values[i]).append(",");
        }
        if (buffer.length() > 0) {
            return buffer.toString().substring(0, buffer.length() - 1);
        }
        return "";
    }
    
    /**
     * Is empty.
     *
     * @param s the s
     * @return true, if is empty
     */
    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    /**
     * Is trimed empty.
     *
     * @param s the s
     * @return true, if is trimed empty
     */
    public static boolean isTrimedEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    /**
     * Is not empty.
     *
     * @param s the s
     * @return true, if is not empty
     */
    public static boolean isNotEmpty(String s) {
        return s != null && s.length() > 0;
    }

	/**
	 * Is line broken.
	 *
	 * @param s the s
	 * @return true, if is line broken
	 */
	public static boolean isLineBroken(String s) {
		if ( null == s ) {
			return false;
		}
		if  (s.contains("\n")) {
			return true;
		}
		if (s.contains("\r\n")) {
			return true;
		}
		return false;
	}

}

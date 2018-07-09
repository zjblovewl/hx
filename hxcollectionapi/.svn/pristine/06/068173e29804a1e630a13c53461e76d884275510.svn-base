package cn.com.base.util;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 3DES加密工具类（与重庆基地同步数据接口用，其它功能如要用到此加密功能，请慎重）
 * 本代码源头是从统一平台项目中拷贝而来
 */
public class Des3 {
	// 密钥
	public static String secretKey = "DC2928A12216BE086836C44E";
	
	// 向量
	private final static String iv = "01234567";
	// 加解密统一使用的编码方式
	private final static String encoding = "GBK";
	
	/**
     * 3DES加密
     * 
     * @param plainText
     *            普通文本
     * @return
     * @throws DESException
     */
    public static String encode(String plainText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);       
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
        return byte2Hex(encryptData);
    }

	public static String decode(String encryptText) throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.DECRYPT_MODE, deskey);
        return new String(cipher.doFinal(hex2Byte(encryptText)),encoding);
    }
	
	public static String byte2Hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
	
	public static byte[] hex2Byte(String str) {
		if (str == null)
			return null;
		str = str.trim();
		int len = str.length();
		if (len == 0 || len % 2 == 1)
			return null;
		byte[] b = new byte[len / 2];
		try {
			for (int i = 0; i < str.length(); i += 2) {
				b[i / 2] = (byte) Integer
						.decode("0x" + str.substring(i, i + 2)).intValue();
			}
			return b;
		} catch (Exception e) {
			return null;
		}
	}
	
//	public static void main(String[] args) {
//		try {
//			String str = encode("aaa");
//			System.out.println(str);
//			System.out.println(decode(str));
//		} catch (BadPaddingException des) {
//			des.printStackTrace();
//			System.out.println("解析密文出错");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}

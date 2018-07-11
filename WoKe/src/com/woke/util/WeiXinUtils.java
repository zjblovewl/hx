package com.woke.util;

import java.util.Formatter;
import java.util.UUID;

/**
 * 微信辅助帮助类 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  jack
 * @version  [版本号, 2017年7月13日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WeiXinUtils
{
    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}

package cn.com.third.alipay.utils;

import cn.com.util.Configuration;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @类功能说明：支付宝基础初始化工具类
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/4/27 上午14:21
 * @版本：V1.0
 */
public class AlipayBaseClient  {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    //应用ID
    public static final String APP_ID = Configuration.getInstance().getValue("appid");

    //请求网关
    public static final String REQUEST_URL = Configuration.getInstance().getValue("appid");

    //私钥
    public static final String APP_PRIVATE_KEY = Configuration.getInstance().getValue("private_key");

    //公钥
    public static final String ALIPAY_PUBLIC_KEY = Configuration.getInstance().getValue("public_key");

    //App支付回调地址
    public static final String ALIPAY_PAY_NOTIFY_URL = Configuration.getInstance().getValue("app_pay_notify_url");

    //数据传输类型
    public static final String DATA_TYPE = "json";

    //数据编码格式
    public static final String CHARSET = "utf-8";

    //加密方式
    public static final String ENCRYPT_METHOD = "RSA2";

    public static AlipayClient defaultClient = null;

    /**
     * @description 获取支付宝请求Client
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/27 14:35:51
     * @author:zhoujinbing
     */
    public static synchronized AlipayClient getInstance()
    {
        if(defaultClient == null)
        {
            defaultClient = new DefaultAlipayClient(REQUEST_URL,APP_ID,APP_PRIVATE_KEY,DATA_TYPE,CHARSET,ALIPAY_PUBLIC_KEY,ENCRYPT_METHOD);
            return defaultClient;
        }
        return defaultClient;
    }










}

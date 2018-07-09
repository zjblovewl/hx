package cn.com.third.alipay.utils;

import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @类功能说明：支付宝服务端预支付工具类
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/4/27 上午14:15
 * @版本：V1.0
 */
public class AlipayTradeAppPayUtils{

    private static final Logger log = LoggerFactory.getLogger(AlipayTradeAppPayUtils.class);

    /**
     * @description 生成APP支付订单
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/27 14:40:46
     * @author:zhoujinbing
     */
    public static String createAlipayPayOrder(AlipayTradeAppPayModel model)
    {
        try {
            //当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest payRequest = new AlipayTradeAppPayRequest();
            log.info("支付订单请求参数: " + AlipayBaseClient.objectMapper.writeValueAsString(model));
            payRequest.setBizModel(model);
            payRequest.setNotifyUrl(AlipayBaseClient.ALIPAY_PAY_NOTIFY_URL);
            AlipayTradeAppPayResponse response = AlipayBaseClient.getInstance().sdkExecute(payRequest);
            log.info("支付订单返回参数:{}",response.getBody());
            return response.getBody();

        }catch (Exception e)
        {
            log.error("生成App支付订单出错,异常为:{}",e.getMessage());
            e.printStackTrace();
            return "";
        }

    }





}

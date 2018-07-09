package cn.com.third.ping.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.base.util.BaseLogger;
import net.sf.json.JSONObject;

import org.apache.commons.net.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.exception.RateLimitException;
import com.pingplusplus.model.Charge;
import com.pingplusplus.model.Event;
import com.pingplusplus.model.PingppObject;
import com.pingplusplus.model.Webhooks;

import cn.com.base.constant.BaseConstant;
import cn.com.base.util.StringUtil;
import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamVo;
import cn.com.third.ping.dao.ChargeOrdersMapper;
import cn.com.third.ping.service.ChargeOrdersService;
import cn.com.util.CommonUtils;
import cn.com.util.Configuration;
import cn.jiguang.common.resp.APIConnectionException;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：nanrui  
 * @创建时间：2018年6月1日 下午3:20:54 
 * @版本：V1.0  
 */
@Service
public class ChargeOrdersServiceImpl extends BaseLogger implements ChargeOrdersService{

	@Autowired
    private BaseConstant baseConstant;
	
	@Autowired
	private ChargeOrdersMapper chargeOrdersMapper;
	
	/**	 
	 * @description 创建订单 
	 * @method  saveOrder
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年6月1日 上午15:52:08
	 * @author:nanrui
	 */
	public ResponseParamVo getPrepaymentCodeById(RequestParamVo vo){
		try{
			Map<String,Object> reusltMap = new HashMap<>();
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();      
			Map<String, Object> orderInfo = chargeOrdersMapper.getOrderPriceById(bodyMap);
			int order_status = Integer.parseInt(orderInfo.get("order_status").toString());
			if(order_status != 1) return StringUtil.returnResponseVo(baseConstant.getRightCode(),"订单已完成","","",new JSONObject());
			// 设置 API Key
		    Pingpp.apiKey = Configuration.getInstance().getValue("pingplusplus_secretkey");
		    // 设置私钥路径，用于请求签名
		    Pingpp.privateKey = Configuration.getInstance().getValue("pingplusplus_privatekey");
		    
			double amount = Double.parseDouble(orderInfo.get("pay_money").toString());
	        Map<String, Object> chargeMap = new HashMap<String, Object>();
	        chargeMap.put("amount", amount * 100);//订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填 100）
	        chargeMap.put("currency", "cny");
	        chargeMap.put("subject", "商品标题测试");
	        chargeMap.put("body", "商品信息测试");
	        chargeMap.put("order_no", orderInfo.get("order_code").toString());// 推荐使用 8-20 位，要求数字或字母，不允许其他字符
	        chargeMap.put("channel", bodyMap.get("channel").toString());// 支付使用的第三方支付渠道取值，请参考：https://www.pingxx.com/api#api-c-new
	        chargeMap.put("client_ip", "127.0.0.1"); // 发起支付请求客户端的 IP 地址，格式为 IPV4，如: 127.0.0.1
	        Map<String, String> app = new HashMap<String, String>();
	        app.put("id", Configuration.getInstance().getValue("pingplusplus_appID"));
	        chargeMap.put("app", app);

	        // extra 取值请查看相应方法说明
	        chargeMap.put("extra", channelExtra(bodyMap.get("channel").toString()));

	        try {
	        	Charge charge = null;
	            //发起交易请求
	            charge = Charge.create(chargeMap);
	            if(charge != null)
	            {
	            	String third_precode =  charge.getId();
	            	int channelCode = getChannelCode(charge.getChannel());
	            	Map<String,Object> saveDataMap = new HashMap<String, Object>();
	            	saveDataMap.put("id", CommonUtils.getUUID());
	            	saveDataMap.put("user_id", orderInfo.get("buyer_id").toString());
	            	saveDataMap.put("third_precode", third_precode);
	            	saveDataMap.put("order_id", orderInfo.get("order_id").toString());
	            	saveDataMap.put("pay_type", channelCode);
	            	saveDataMap.put("transaction_price", amount);
	            	chargeOrdersMapper.saveChargeOrderFlow(saveDataMap);
	               
	            	if(channelCode == 2 || channelCode == 4)
	            	{
	            		Map<String,Object> credential = charge.getCredential();
	            		String qr_url = "";
	            		if(channelCode == 2) qr_url = credential.get("alipay_qr").toString();
	            		if(channelCode == 4) qr_url = credential.get("wx_pub_qr").toString();
	            		reusltMap.put("qr_url", qr_url);
	            	}
	            	else
	            	{
	            		String chargeString = charge.toString();
	            		reusltMap.put("charge", chargeString);
	            	}
	            }
	            
	        } catch (ChannelException e) {
//	            e.printStackTrace();
	            log.error("获取订单预付码异常",e.getMessage());
	            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"获取订单预付码异常-"+e.getMessage(),"","",new JSONObject());
	        } catch (RateLimitException e) {
//	            e.printStackTrace();
	            log.error("获取订单预付码异常",e.getMessage());
	            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"获取订单预付码异常-"+e.getMessage(),"","",new JSONObject());
	        } catch (AuthenticationException e) {
//	            e.printStackTrace();
	            log.error("获取订单预付码异常",e.getMessage());
	            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"获取订单预付码异常-"+e.getMessage(),"","",new JSONObject());
	        } catch (APIException e) {
//	            e.printStackTrace();
	            log.error("获取订单预付码异常",e.getMessage());
	            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"获取订单预付码异常-"+e.getMessage(),"","",new JSONObject());
	        } catch (InvalidRequestException e) {
//	            e.printStackTrace();
	            log.error("获取订单预付码异常",e.getMessage());
	            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"获取订单预付码异常-"+e.getMessage(),"","",new JSONObject());
	        }catch (Exception e) {
//	            e.printStackTrace();
	            log.error("获取订单预付码异常",e.getMessage());
	            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"获取订单预付码异常-"+e.getMessage(),"","",new JSONObject());
	        }
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"获取订单预付码成功","","",reusltMap);               
        }catch (Exception e)
        {
            log.error("获取订单预付码异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"获取订单预付码异常","","",new JSONObject());
        }
	}
	private Map<String, Object> channelExtra(String channel) {
        Map<String, Object> extra = new HashMap<>();

        switch (channel) {
            case "alipay":
                extra = alipayExtra();
                break;
            case "alipay_qr":
                extra = alipayQrExtra();
                break;
            case "wx":
                extra = wxExtra();
                break;
            case "wx_pub_qr":
                extra = wxPubQrDirectExtra();
                break;
            case "wx_lite":
                extra = wxLiteExtra();
                break;
        }
        return extra;
    }
	// extra 根据渠道会有不同的参数
    private Map<String, Object> alipayExtra() {
        Map<String, Object> extra = new HashMap<>();

        // 可选，开放平台返回的包含账户信息的 token（授权令牌，商户在一定时间内对支付宝某些服务的访问权限）。通过授权登录后获取的  alipay_open_id ，作为该参数的  value ，登录授权账户即会为支付账户，32 位字符串。
        // extra.put("extern_token", "TOKEN");

        // 可选，是否发起实名校验，T 代表发起实名校验；F 代表不发起实名校验。
        extra.put("rn_check", "T");

        return extra;
    }


    private Map<String, Object> alipayQrExtra() {
        Map<String, Object> extra = new HashMap<>();

        return extra;
    }


    private Map<String, Object> wxExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 可选，指定支付方式，指定不能使用信用卡支付可设置为 no_credit 。
        extra.put("limit_pay", "no_credit");

        // 可选，商品标记，代金券或立减优惠功能的参数。
        // extra.put("goods_tag", "YOUR_GOODS_TAG");

        return extra;
    }
    
    private Map<String, Object> wxPubQrDirectExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 可选，指定支付方式，指定不能使用信用卡支付可设置为 no_credit 。
        extra.put("limit_pay", "no_credit");

        // 可选，商品标记，代金券或立减优惠功能的参数。
        // extra.put("goods_tag", "YOUR_GOODS_TAG");

        // 必须，商品 ID，1-32 位字符串。此 id 为二维码中包含的商品 ID，商户可自定义。
        extra.put("product_id", "YOUR_PRODUCT_ID");

        return extra;
    }

    private Map<String, Object> wxLiteExtra() {
        Map<String, Object> extra = new HashMap<>();
        // 可选，指定支付方式，指定不能使用信用卡支付可设置为 no_credit 。
        extra.put("limit_pay", "no_credit");

        // 可选，商品标记，代金券或立减优惠功能的参数。
        // extra.put("goods_tag", "YOUR_GOODS_TAG");

        // 必须，用户在商户 appid 下的唯一标识。
        extra.put("open_id", "o7xEMsySBFG3MVHI-9VsAJX-j50W");

        return extra;
    }
    public int getChannelCode(String channel)
    {
    	//1、支付宝app 2、支付宝扫码 3、微信app 4、微信扫码 5、微信小程序'
    	int channelCode = 0;
    	switch(channel)
    	{
    		case "alipay":
    			channelCode = 1;
    			break;
    		case "alipay_qr":
    			channelCode = 2;
    			break;
    		case "wx":
    			channelCode = 3;
    			break;
    		case "wx_pub_qr":
    			channelCode = 4;
    			break;
    		case "wx_lite": 
    			channelCode = 5;
    			break;
    	}
    	return channelCode;
    }
    
    public int payBack(HttpServletRequest request)
    {
    	
    	try{
    		request.setCharacterEncoding("UTF8");
            //获取头部所有信息
            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                String value = request.getHeader(key);
                System.out.println(key+" "+value);
            }
            // 获得 http body 内容
            BufferedReader reader = request.getReader();
            StringBuffer buffer = new StringBuffer();
            String string;
            while ((string = reader.readLine()) != null) {
                buffer.append(string);
            }
            reader.close();
            
            // 该数据请从 request 中获取原始 POST 请求数据
            String webhooksRawPostData = request.toString();
            System.out.println("------- POST 原始数据 -------");
            System.out.println(webhooksRawPostData);
            // 签名数据请从 request 的 header 中获取, key 为 X-Pingplusplus-Signature (请忽略大小写, 建议自己做格式化)
            String signature = request.getHeader("x-pingplusplus-signature");
            System.out.println("------- 签名 -------");
            System.out.println(signature);
            boolean result = verifyData(webhooksRawPostData, signature, getPubKey());
            if(!result) // 验签失败
            {
            	log.error("验签失败",webhooksRawPostData);
            	return 10003; //验签失败
            }
            // 解析异步通知数据
            Event event = Webhooks.eventParse(buffer.toString());
            
            Charge charge_object = (Charge)event.getData().getObject();
            String order_no = charge_object.getOrderNo(); // 商品订单号
            log.info("验签成功===========商品订单号order_code：",order_no);
            int amount = charge_object.getAmount(); // 商品订单价格
            String transaction_no = charge_object.getTransactionNo(); // 支付渠道返回的交易流水号
            String third_flowcode = charge_object.getId();
            log.info("验签成功===========支付渠道返回的交易流水号：",order_no);
            log.info("验签成功===========第三方订单ID：",order_no);
            Map<String,Object> selectDataMap = new HashMap<String, Object>();
            selectDataMap.put("order_code", order_no);
            Map<String, Object> orderInfo = chargeOrdersMapper.getOrderPriceByOrderCode(selectDataMap);
            if ("charge.succeeded".equals(event.getType())) 
            {
                // 支付成功
            	double order_amount = Double.parseDouble(orderInfo.get("pay_money").toString());
            	if(order_amount * 100 != amount)
            	{
            		log.error("金额不符","交易金额："+amount+"======订单金额："+order_amount);
                	return 10004; 
            	}
            	int order_status = Integer.parseInt(orderInfo.get("order_status").toString());
    			if(order_status != 1)
				{
    				log.error("订单状态不对","订单状态："+order_status);
                	return 10005; 
				}
    			int channel = getChannelCode(charge_object.getChannel());
    			
    			Map<String,Object> saveDataMap = new HashMap<String, Object>();
    			saveDataMap.put("order_id", orderInfo.get("order_id"));
    			saveDataMap.put("pay_method", channel);
    			saveDataMap.put("third_flowcode", third_flowcode);
    			saveDataMap.put("channel_flowcode", transaction_no);
    			saveDataMap.put("transaction_state", 1);
    			chargeOrdersMapper.changeOrderStatus(saveDataMap);
    			chargeOrdersMapper.changeOrderFlowStatus(saveDataMap);
    			
    			//购买
    			Map<String,Object> saveDataMap_1 = new HashMap<String, Object>();
    			saveDataMap_1.put("id", CommonUtils.getUUID());
    			saveDataMap_1.put("user_id", orderInfo.get("buyer_id"));
    			saveDataMap_1.put("order_code", orderInfo.get("order_id"));
    			saveDataMap_1.put("money", orderInfo.get("pay_money"));
    			saveDataMap_1.put("inout_type", channel);
    			chargeOrdersMapper.saveIncomeOutRecord(saveDataMap_1);
    			//售出
    			Map<String,Object> saveDataMap_2 = new HashMap<String, Object>();
    			saveDataMap_2.put("id", CommonUtils.getUUID());
    			saveDataMap_2.put("user_id", orderInfo.get("seller_id"));
    			saveDataMap_2.put("order_code", orderInfo.get("order_id"));
    			saveDataMap_2.put("money", orderInfo.get("pay_money"));
    			saveDataMap_2.put("inout_type", 6);
    			chargeOrdersMapper.saveIncomeOutRecord(saveDataMap_2);
    			
    			log.info("支付成功=================商品订单号order_code：",order_no);
    			return 10001;
            } 
            else if ("refund.succeeded".equals(event.getType())) 
            {
               // 退款成功
            	log.info("退款成功=================商品订单号order_code：",order_no);
            	return 10002;
            } 
            else 
            {
            	log.error("订单回调处理异常",webhooksRawPostData);
            	return 10006;
            }   
           
        }catch (Exception e)
        {
            log.error("订单回调处理异常",e.getMessage());
            return 10006;
        }
    	
    }
    
    /**
     * 获得公钥
     * @return
     * @throws Exception
     */
    private static PublicKey getPubKey() throws Exception {
        String pubKeyString = Configuration.getInstance().getValue("pingplusplus_publickey");
        pubKeyString = pubKeyString.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
        byte[] keyBytes = Base64.decodeBase64(pubKeyString);

        // generate public key
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(spec);
        return publicKey;
    }

    /**
     * 验证签名
     * @param dataString
     * @param signatureString
     * @param publicKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    private static boolean verifyData(String dataString, String signatureString, PublicKey publicKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        byte[] signatureBytes = Base64.decodeBase64(signatureString);
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(dataString.getBytes("UTF-8"));
        return signature.verify(signatureBytes);
    }
}

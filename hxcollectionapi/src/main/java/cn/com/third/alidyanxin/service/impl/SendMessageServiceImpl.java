package cn.com.third.alidyanxin.service.impl;

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
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.base.util.BaseLogger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.net.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
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
import cn.com.third.alidyanxin.dao.SendMessageMapper;
import cn.com.third.alidyanxin.service.SendMessageService;
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
public class SendMessageServiceImpl extends BaseLogger implements SendMessageService{

	
	
	
	@Autowired
    private BaseConstant baseConstant;
	
	@Autowired
	private SendMessageMapper sendMessageMapper;
	
	@Autowired
	private RedisTemplate redisTemplate;
	/**	 
	 * @description 发送短信验证码 
	 * @method  sendVerificationCode
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年6月25日 上午15:52:08
	 * @author:nanrui
	 */
	public ResponseParamVo sendVerificationCode(RequestParamVo vo){
		try{
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();      
			String phoneNum = bodyMap.get("phoneNum").toString();
			String type = bodyMap.get("type").toString();
			if(StringUtil.isNullOrBlank(phoneNum))
	        {
	            log.error("发送短信，手机号为空");
	            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"手机号为空","","","");
	        }
			String str_code = (String)redisTemplate.opsForValue().get(phoneNum+"_"+type);
			if(!StringUtil.isEmptyOrNull(str_code)) 
			{
				String[] code_array =str_code.split("_");
				long ts = Long.parseLong(code_array[1]);
				long now_ts = new Date().getTime();
				if((now_ts - ts) < (60 *1000)) 
				{
					return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"验证码发送时间未超过1分钟","","","");
				}
			}
			String random = CommonUtils.Random(6);
			SendSmsResponse sendSmsResponse = sendSms(phoneNum,random);
			Map<String,Object> reusltMap = new HashMap<>();
			reusltMap.put("phoneNum", phoneNum);
			reusltMap.put("type", type);
			if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) 
			{
				 //请求成功
				String code = random + "_" + (new Date()).getTime();
				redisTemplate.opsForValue().set(phoneNum+"_"+type,code);
				return StringUtil.returnResponseVo(baseConstant.getRightCode(),"验证码发送成功","","",reusltMap);  
			}
			log.error("验证码发送失败",sendSmsResponse.getCode());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"验证码发送失败","","",reusltMap);       
        }catch (Exception e)
        {
            log.error("验证码发送异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"验证码发送异常","","",new JSONObject());
        }
	}
	
	/**	 
	 * @description 校验手机验证码 
	 * @method  checkVerificationCode
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年6月25日 上午15:52:08
	 * @author:nanrui
	 */
	public ResponseParamVo checkVerificationCode(RequestParamVo vo){
		try{
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody(); 
			String phoneNum = bodyMap.get("phoneNum").toString();
			String code = bodyMap.get("message_code").toString();
			String type = bodyMap.get("type").toString();
			
			if(StringUtil.isNullOrBlank(phoneNum))
	        {
	            log.error("验证手机验证码，手机号为空");
	            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"手机号为空","","","");
	        }
			if(StringUtil.isNullOrBlank(code))
	        {
	            log.error("验证手机验证码，验证码为空");
	            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"验证码为空","","","");
	        }
			if(StringUtil.isNullOrBlank(type))
	        {
	            log.error("验证手机验证码，验证码类型为空");
	            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"验证码类型为空","","","");
	        }
			
			String str_code = redisTemplate.opsForValue().get(phoneNum+"_"+type).toString();
			if(StringUtil.isNullOrBlank(str_code)) return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"该手机号无验证码","","","");
			String[] code_array =str_code.split("_");
			String ser_code = code_array[0];
			long ts = Long.parseLong(code_array[1]);
			long now_ts = new Date().getTime();
			if(!ser_code.equals(code)) return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"验证码错误","","","");
			if((now_ts - ts) > (10 * 60 *1000)) return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"验证码时间超过10分钟","","","");
			
			redisTemplate.delete(phoneNum+"_"+type);
			Map<String,Object> reusltMap = new HashMap<>();
			reusltMap.put("phoneNum", phoneNum);
			reusltMap.put("code", code);
			reusltMap.put("type", type);
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"验证码验证成功","","",reusltMap);               
        }
		catch (Exception e)
        {
        	
            log.error("获取订单预付码异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"验证码验证码异常","","",new JSONObject());
        }
	}
    
	
	
	private static SendSmsResponse sendSms(String phoneNum,String random) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

      //产品名称:云通信短信API产品,开发者无需替换
        String product = Configuration.getInstance().getValue("ali_product");
        //产品域名,开发者无需替换
        String domain = Configuration.getInstance().getValue("ali_domain");

        // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
        String accessKeyId = Configuration.getInstance().getValue("ali_accessKeyId");
        String accessKeySecret = Configuration.getInstance().getValue("ali_accessKeySecret");
        
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phoneNum);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("乾隆收藏");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_137955093");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{ \"code\":\""+random+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
}

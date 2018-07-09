package cn.com.third.alidyanxin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.base.constant.BaseConstant;
import cn.com.base.util.BaseLogger;
import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamHeader;
import cn.com.base.vo.ResponseParamVo;
import cn.com.mobile.order.service.OrderMobileService;
import cn.com.third.alidyanxin.service.SendMessageService;

import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @类功能说明：ali第三方短信接口
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：nanrui
 * @创建时间：2018/6/25 上午14:51
 * @版本：V1.0
 */
@RestController
@RequestMapping("/api/sendMessage")
public class SendMessageController extends BaseLogger {
    
	private ObjectMapper objectMapper = new ObjectMapper();
	
    @Autowired
    private BaseConstant baseConstant;
    
    @Autowired
    private SendMessageService sendMessageService;
    /**
     * @description 发送短信验证码 
     * @method  sendVerificationCode
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年6月4日 上午11:01:04
     * @author:nanrui
     */
	@RequestMapping(value = "/sendVerificationCode",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getPrepaymentCodeById(HttpServletRequest request){
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");         
        RequestParamVo vo = null;
        try {			
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = sendMessageService.sendVerificationCode(vo);             
        } catch (Exception e) {
            return getResponseParamVo(e);
        }         
        return result;
    }
	/**
     * @description 校验手机验证码 
     * @method  checkVerificationCode
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年6月12日 上午11:01:04
     * @author:nanrui
     */
	@RequestMapping(value = "/checkVerificationCode",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo payBack(HttpServletRequest request){
		ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");         
        RequestParamVo vo = null;
        try {			
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = sendMessageService.checkVerificationCode(vo);  
        
        } catch (Exception e) {
            return getResponseParamVo(e);
        }         
        return result;
    }
	/**
     * @description 返回错误结果封装方法
     * @method  getResponseParamVo
     * @param  * @param e
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/3/30 09:25:01
     * @author:zhoujinbing
     */
    private ResponseParamVo getResponseParamVo(Exception e) {
        ResponseParamVo result;
        result = new ResponseParamVo();
        ResponseParamHeader header = new ResponseParamHeader();
        header.setResp_code(baseConstant.getErrorCode());
        header.setResp_msg("解密后的请求参数解析成对象出错。");
        result.setHeader(header);
        log.error((String) header.getResp_msg(), e);
        return result;
    }
}

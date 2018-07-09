package cn.com.third.ping.controller;

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
import cn.com.third.ping.service.ChargeOrdersService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingplusplus.model.Charge;

/**
 * @类功能说明：ping++第三方支付接口
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/4/13 上午14:51
 * @版本：V1.0
 */
@RestController
@RequestMapping("/api/chargeOrders")
public class ChargeOrdersController extends BaseLogger {
    
	private ObjectMapper objectMapper = new ObjectMapper();
	
    @Autowired
    private BaseConstant baseConstant;
    
    @Autowired
    private ChargeOrdersService chargeOrdersService;
    /**
     * @description 获取订单预付信息 
     * @method  getPrepaymentCodeById
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年6月4日 上午11:01:04
     * @author:nanrui
     */
	@RequestMapping(value = "/getPrepaymentCodeById",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getPrepaymentCodeById(HttpServletRequest request){
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");         
        RequestParamVo vo = null;
        try {			
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = chargeOrdersService.getPrepaymentCodeById(vo);             
        } catch (Exception e) {
            return getResponseParamVo(e);
        }         
        return result;
    }
	/**
     * @description 第三方支付异步回调 
     * @method  payBack
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年6月12日 上午11:01:04
     * @author:nanrui
     */
	@RequestMapping(value = "/payBack",method = RequestMethod.POST)
    public @ResponseBody void payBack(HttpServletRequest request,HttpServletResponse response){
		int result = 0; // 10001-支付成功   10002-退款成功 10003-验签失败 10004-金额不符  10005-订单状态不对 10006-其他错误-
        result = chargeOrdersService.payBack(request);  
        
        if (result == 10001 || result == 10002) {
            response.setStatus(200);// 支付成功
        } else {
            response.setStatus(500); // 其他错误
        }      
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

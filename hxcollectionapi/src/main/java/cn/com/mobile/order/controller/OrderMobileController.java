package cn.com.mobile.order.controller;

import javax.servlet.http.HttpServletRequest;

import cn.com.base.util.BaseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.base.constant.BaseConstant;
import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamHeader;
import cn.com.base.vo.ResponseParamVo;
import cn.com.mobile.order.service.OrderMobileService;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @类功能说明：订单控制器
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/4/9 上午9:37
 * @版本：V1.0
 */
@RestController
@RequestMapping("/api/ordermobile")
public class OrderMobileController extends BaseLogger{
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
    @Autowired
    private BaseConstant baseConstant;
    
    @Autowired
    private OrderMobileService orderMobileService;
    
    /**
     * @description 创建订单 
     * @method  saveOrder
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年5月8日 上午11:01:04
     * @author:chenchen
     */
    @RequestMapping(value = "/saveOrder",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo saveOrder(HttpServletRequest request){
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");         
        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = orderMobileService.saveOrder(vo);             
        } catch (Exception e) {
            return getResponseParamVo(e);
        }         
        return result;
    }
    
    /**
     * @description 售出记录（已完成的订单）
     * @method  getSellOrderRecords
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年5月9日 下午3:09:36
     * @author:chenchen
     */
    @RequestMapping(value = "/getSellOrderRecords",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getSellOrderRecords(HttpServletRequest request){
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");         
        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = orderMobileService.getSellOrderRecords(vo);             
        } catch (Exception e) {
            return getResponseParamVo(e);
        }         
        return result;
    }
    
    /**
     * @description 购买记录（已完成的订单
     * @method  getBuyOrderRecords
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年5月9日 下午3:09:40
     * @author:chenchen
     */
    @RequestMapping(value = "/getBuyOrderRecords",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getBuyOrderRecords(HttpServletRequest request){
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");         
        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = orderMobileService.getBuyOrderRecords(vo);             
        } catch (Exception e) {
            return getResponseParamVo(e);
        }         
        return result;
    }
    
    /**
     * @description 我售出的
     * @method  getMySellOrderRecords
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年5月10日 上午10:13:18
     * @author:chenchen
     */
    @RequestMapping(value = "/getMySellOrderRecords",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getMySellOrderRecords(HttpServletRequest request){
	    ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");         
        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = orderMobileService.getMySellOrderRecords(vo);             
        } catch (Exception e) {
            return getResponseParamVo(e);
        }         
        return result;
    }
    
    /**
     * @description 我购买的
     * @method  getMySellOrderRecords
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年5月10日 上午10:13:18
     * @author:chenchen
     */
    @RequestMapping(value = "/getMyBuyOrderRecords",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getMyBuyOrderRecords(HttpServletRequest request){
	    ResponseParamVo result = null;
	    String requestJson = (String) request.getAttribute("requestJson");         
	    RequestParamVo vo = null;
	    try {
	        vo = objectMapper.readValue(requestJson, RequestParamVo.class);
	        result = orderMobileService.getMyBuyOrderRecords(vo);             
	    } catch (Exception e) {
	        return getResponseParamVo(e);
	    }         
	    return result;
    }
    
    /**
     * @description 查询订单详情
     * @method  getOrderInfoById
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年5月15日 下午4:08:47
     * @author:chenchen
     */
    @RequestMapping(value = "/getOrderInfoById",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getOrderInfoById(HttpServletRequest request){
    	ResponseParamVo result = null;
 	    String requestJson = (String) request.getAttribute("requestJson");         
 	    RequestParamVo vo = null;
 	    try {
 	        vo = objectMapper.readValue(requestJson, RequestParamVo.class);
 	        result = orderMobileService.getOrderInfoById(vo);             
 	    } catch (Exception e) {
 	        return getResponseParamVo(e);
 	    }         
 	    return result;
    }
    
    /**
     * @description 发货 
     * @method  sendOutGoods
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年5月15日 下午5:36:41
     * @author:chenchen
     */
    @RequestMapping(value = "/sendOutGoods",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo sendOutGoods(HttpServletRequest request){
    	ResponseParamVo result = null;
 	    String requestJson = (String) request.getAttribute("requestJson");         
 	    RequestParamVo vo = null;
 	    try {
 	        vo = objectMapper.readValue(requestJson, RequestParamVo.class);
 	        result = orderMobileService.sendOutGoods(vo);             
 	    } catch (Exception e) {
 	        return getResponseParamVo(e);
 	    }         
 	    return result;
    }
    
    /**
     * @description 订单评价 
     * @method  saveOrderEvaluate
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年5月16日 下午2:09:49
     * @author:chenchen
     */
    @RequestMapping(value = "/saveOrderEvaluate",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo saveOrderEvaluate(HttpServletRequest request){
    	ResponseParamVo result = null;
 	    String requestJson = (String) request.getAttribute("requestJson");         
 	    RequestParamVo vo = null;
 	    try {
 	        vo = objectMapper.readValue(requestJson, RequestParamVo.class);
 	        result = orderMobileService.saveOrderEvaluate(vo);             
 	    } catch (Exception e) {
 	        return getResponseParamVo(e);
 	    }         
 	    return result;
    }
    
    /**
     * @description 取消订单 
     * @method  cancelOrder
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年5月16日 下午5:14:06
     * @author:chenchen
     */
    @RequestMapping(value = "/cancelOrder",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo cancelOrder(HttpServletRequest request){
    	ResponseParamVo result = null;
 	    String requestJson = (String) request.getAttribute("requestJson");         
 	    RequestParamVo vo = null;
 	    try {
 	        vo = objectMapper.readValue(requestJson, RequestParamVo.class);
 	        result = orderMobileService.cancelOrder(vo);             
 	    } catch (Exception e) {
 	        return getResponseParamVo(e);
 	    }         
 	    return result;
    }
    
    /**
     * @description 确认收货 
     * @method  confirmCollectGoods
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年5月16日 下午5:44:35
     * @author:chenchen
     */
    @RequestMapping(value = "/confirmCollectGoods",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo confirmCollectGoods(HttpServletRequest request){
    	ResponseParamVo result = null;
 	    String requestJson = (String) request.getAttribute("requestJson");         
 	    RequestParamVo vo = null;
 	    try {
 	        vo = objectMapper.readValue(requestJson, RequestParamVo.class);
 	        result = orderMobileService.confirmCollectGoods(vo);             
 	    } catch (Exception e) {
 	        return getResponseParamVo(e);
 	    }         
 	    return result;
    }
    
    /**
     * @description 申请退款
     * @method  applyRefund
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年5月17日 上午9:44:29
     * @author:chenchen
     */
    @RequestMapping(value = "/applyRefund",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo applyRefund(HttpServletRequest request){
    	ResponseParamVo result = null;
 	    String requestJson = (String) request.getAttribute("requestJson");         
 	    RequestParamVo vo = null;
 	    try {
 	        vo = objectMapper.readValue(requestJson, RequestParamVo.class);
 	        result = orderMobileService.applyRefund(vo);             
 	    } catch (Exception e) {
 	        return getResponseParamVo(e);
 	    }         
 	    return result;
    }
    
    /**
     * @description 同意退款 
     * @method  agreeRefund
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年5月17日 上午9:56:34
     * @author:chenchen
     */
    @RequestMapping(value = "/agreeRefund",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo agreeRefund(HttpServletRequest request){
    	ResponseParamVo result = null;
 	    String requestJson = (String) request.getAttribute("requestJson");         
 	    RequestParamVo vo = null;
 	    try {
 	        vo = objectMapper.readValue(requestJson, RequestParamVo.class);
 	        result = orderMobileService.agreeRefund(vo);             
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

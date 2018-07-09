package cn.com.pc.transaction.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.base.util.BaseLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.base.constant.BaseConstant;
import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamHeader;
import cn.com.base.vo.ResponseParamVo;
import cn.com.mobile.message.service.MessageMobileService;
import cn.com.mobile.transaction.service.TransactionMobileService;
import cn.com.pc.transaction.service.TransactionPcService;

/**  
 * @类功能说明：藏品pc端controller
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年4月16日 上午10:01:17 
 * @版本：V1.0  
 */
@RestController
@RequestMapping("/api/transactionpc")
public class TransactionPcController extends BaseLogger{


	@Autowired
	private BaseConstant baseConstant;
	
	@Autowired
	private TransactionPcService transactionPcService;
	
	@Autowired
	private MessageMobileService messageMobileService;
	
	@Autowired
    private TransactionMobileService transactionMobileService;
	
	/**
	 * @description 首页藏品广告 
	 * @method  getHomePageAdvertisementRecords
	 * @param @param request
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月16日 上午10:35:25
	 * @author:chenchen
	 */
	@RequestMapping(value = "/getHomePageAdvertisementRecords",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getHomePageAdvertisementRecords(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");		
		response.setContentType("text/html; charset=UTF-8");
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = transactionPcService.getHomePageAdvertisementRecords(vo);             
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }
	
	/**
	 * @description 首页公告（系统消息）
	 * @method  getSysMessageRecords
	 * @param @param request
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月16日 下午2:29:36
	 * @author:chenchen
	 */
	@RequestMapping(value = "/getSysMessageRecords",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getSysMessageRecords(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html; charset=UTF-8");
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);           
            result = messageMobileService.getSysMessageRecords(vo);             
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }
	
	/**
	 * @description 首页藏品推荐 
	 * @method  getRecommendGoodsRecords
	 * @param @param request
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月18日 上午11:31:10
	 * @author:chenchen
	 */
	@RequestMapping(value = "/getRecommendGoodsRecords",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getRecommendGoodsRecords(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html; charset=UTF-8");
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = transactionPcService.getRecommendGoodsRecords(vo);             
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }
	
	/**
	 * @description 首页卖家推荐 
	 * @method  getRecommendSellerRecords
	 * @param @param request
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月18日 下午4:13:04
	 * @author:chenchen
	 */
	@RequestMapping(value = "/getRecommendSellerRecords",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getRecommendSellerRecords(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html; charset=UTF-8");
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = transactionPcService.getRecommendSellerRecords(vo);             
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }	
	
	/**
	 * @description 查询收藏品数据 
	 * @method  getGoodsRecords
	 * @param @param request
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月19日 下午3:12:42
	 * @author:chenchen
	 */
	 @RequestMapping(value = "/getGoodsRecords",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo getGoodsRecords(HttpServletRequest request,HttpServletResponse response){
		 response.setHeader("Access-Control-Allow-Origin","*");
		 response.setContentType("text/html; charset=UTF-8");
         ResponseParamVo result = null;
         String requestJson = (String) request.getAttribute("requestJson");
         RequestParamVo vo = null;
         try {
             vo = objectMapper.readValue(requestJson, RequestParamVo.class);
             result = transactionPcService.getGoodsRecords(vo);             
         } catch (Exception e) {
             return getResponseParamVo(e);
         }
         return result;
     }
	 
	 /**
      * @description 查询收藏品详情 
      * @method  getGoodsInfoById
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月3日 下午4:07:36
      * @author:chenchen
      */
     @RequestMapping(value = "/getGoodsInfoById",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo getGoodsInfoById(HttpServletRequest request,HttpServletResponse response){
    	 response.setHeader("Access-Control-Allow-Origin","*");
    	 response.setContentType("text/html; charset=UTF-8");
    	 ResponseParamVo result = null;
         String requestJson = (String) request.getAttribute("requestJson");
         RequestParamVo vo = null;
         try {
             vo = objectMapper.readValue(requestJson, RequestParamVo.class);
             result = transactionMobileService.getGoodsInfoById(vo);             
         } catch (Exception e) {
             return getResponseParamVo(e);
         }
         return result;
     }
     
     /**
      * @description 获取我的藏品 
      * @method  getMyGoodsRecords
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月20日 下午5:04:41
      * @author:chenchen
      */
     @RequestMapping(value = "/getMyGoodsRecords",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo getMyGoodsRecords(HttpServletRequest request,HttpServletResponse response){
    	 response.setHeader("Access-Control-Allow-Origin","*");
		 response.setContentType("text/html; charset=UTF-8");
    	 ResponseParamVo result = null;
         String requestJson = (String) request.getAttribute("requestJson");
         RequestParamVo vo = null;
         try {
             vo = objectMapper.readValue(requestJson, RequestParamVo.class);
             result = transactionMobileService.getMyGoodsRecords(vo);             
         } catch (Exception e) {
             return getResponseParamVo(e);
         }
         return result;
     }  
     
     /**
      * @description 查询本店铺其他藏品 
      * @method  getMyOtherGoodsRecords
      * @param @param request
      * @param @param response
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月23日 上午10:15:58
      * @author:chenchen
      */
     @RequestMapping(value = "/getMyOtherGoodsRecords",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo getMyOtherGoodsRecords(HttpServletRequest request,HttpServletResponse response){
    	 response.setHeader("Access-Control-Allow-Origin","*");
		 response.setContentType("text/html; charset=UTF-8");
    	 ResponseParamVo result = null;
         String requestJson = (String) request.getAttribute("requestJson");
         RequestParamVo vo = null;
         try {
             vo = objectMapper.readValue(requestJson, RequestParamVo.class);
             result = transactionPcService.getMyOtherGoodsRecords(vo);             
         } catch (Exception e) {
             return getResponseParamVo(e);
         }
         return result;
     }
     
     /**
      * @description 发布收藏品 
      * @method  publishGoods
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月23日 上午10:24:51
      * @author:chenchen
      */
     @RequestMapping(value = "/publishGoods",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo publishGoods(HttpServletRequest request,HttpServletResponse response){
    	 response.setHeader("Access-Control-Allow-Origin","*");
		 response.setContentType("text/html; charset=UTF-8");
         ResponseParamVo result = null;
         String requestJson = (String) request.getAttribute("requestJson");         
         RequestParamVo vo = null;
         try {
             vo = objectMapper.readValue(requestJson, RequestParamVo.class);
             result = transactionMobileService.publishGoods(vo);             
         } catch (Exception e) {
             return getResponseParamVo(e);
         }         
         return result;
     }
	
     /** 
      * @description 查看公告详情 
      * @method  getMessageById
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月23日 下午5:08:05
      * @author:chenchen
      */
     @RequestMapping(value = "/getMessageById",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo getMessageById(HttpServletRequest request,HttpServletResponse response){   
     	 response.setHeader("Access-Control-Allow-Origin","*");
    	 response.setContentType("text/html; charset=UTF-8");
         ResponseParamVo result = null;
         String requestJson = (String) request.getAttribute("requestJson");
         RequestParamVo vo = null;
         try {
             vo = objectMapper.readValue(requestJson, RequestParamVo.class);
             result = messageMobileService.getMessageById(vo);             
         } catch (Exception e) {
             return getResponseParamVo(e);
         }
         return result;
     }
     
     /**
      * @description 我的足迹 
      * @method  getMyTrackRecords
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月24日 下午7:37:27
      * @author:chenchen
      */
     @RequestMapping(value = "/getMyTrackRecords",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo getMyTrackRecords(HttpServletRequest request,HttpServletResponse response){   
     	 response.setHeader("Access-Control-Allow-Origin","*");
     	 response.setContentType("text/html; charset=UTF-8");
         ResponseParamVo result = null;
         String requestJson = (String) request.getAttribute("requestJson");
         RequestParamVo vo = null;
         try {
             vo = objectMapper.readValue(requestJson, RequestParamVo.class);
             result = transactionPcService.getMyTrackRecords(vo);             
         } catch (Exception e) {
             return getResponseParamVo(e);
         }
         return result;
     }
     
     /**
      * @description 商品评论和回复(公用)
      * @method  saveGoodsComment
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月24日 下午7:52:09
      * @author:chenchen
      */
     @RequestMapping(value = "/saveGoodsComment",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo saveGoodsComment(HttpServletRequest request,HttpServletResponse response){
    	 response.setHeader("Access-Control-Allow-Origin","*");
     	 response.setContentType("text/html; charset=UTF-8");
    	 ResponseParamVo result = null;
         String requestJson = (String) request.getAttribute("requestJson");
         RequestParamVo vo = null;
         try {
             vo = objectMapper.readValue(requestJson, RequestParamVo.class);
             result = transactionMobileService.saveGoodsComment(vo);             
         } catch (Exception e) {
             return getResponseParamVo(e);
         }
         return result;
     }
     
     /**      
      * @description 收藏或取消藏品 
      * @method  saveGoodsCollection
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月24日 下午8:07:48
      * @author:chenchen
      */
     @RequestMapping(value = "/saveGoodsCollection",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo saveGoodsCollection(HttpServletRequest request,HttpServletResponse response){
      	 response.setHeader("Access-Control-Allow-Origin","*");
     	 response.setContentType("text/html; charset=UTF-8");
    	 ResponseParamVo result = null;
         String requestJson = (String) request.getAttribute("requestJson");
         RequestParamVo vo = null;
         try {
             vo = objectMapper.readValue(requestJson, RequestParamVo.class);
             result = transactionMobileService.saveGoodsCollection(vo);             
         } catch (Exception e) {
             return getResponseParamVo(e);
         }
         return result;
     }
     
     /**
      * 我的收藏-藏品 
      * @description 
      * @method  getMyCollectionRecords
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月24日 下午8:12:17
      * @author:chenchen
      */
     @RequestMapping(value = "/getMyCollectionRecords",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo getMyCollectionRecords(HttpServletRequest request,HttpServletResponse response){
      	 response.setHeader("Access-Control-Allow-Origin","*");
     	 response.setContentType("text/html; charset=UTF-8");
    	 ResponseParamVo result = null;
         String requestJson = (String) request.getAttribute("requestJson");
         RequestParamVo vo = null;
         try {
             vo = objectMapper.readValue(requestJson, RequestParamVo.class);
             result = transactionMobileService.getMyCollectionRecords(vo);             
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

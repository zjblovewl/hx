package cn.com.mobile.transaction.controller;

import javax.servlet.http.HttpServletRequest;

import cn.com.base.util.BaseLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.base.constant.BaseConstant;
import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamHeader;
import cn.com.base.vo.ResponseParamVo;
import cn.com.mobile.transaction.service.TransactionMobileService;

import com.fasterxml.jackson.databind.ObjectMapper;

/**  
 * @类功能说明：交易controller
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年3月30日 下午3:44:35 
 * @版本：V1.0  
 */
@RestController
@RequestMapping("/api/transactionmobile")
public class TransactionMobileController extends BaseLogger{

     @Autowired
     private BaseConstant baseConstant;
     
     @Autowired
     private TransactionMobileService transactionMobileService;
     
     /**
      * @description 发布收藏品
      * @method  publishGoods
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月2日 上午10:16:42
      * @author:chenchen
      */
     @RequestMapping(value = "/publishGoods",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo publishGoods(HttpServletRequest request){
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
      * @description 查询收藏品列表数据 
      * @method  getGoodsRecords
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月3日 上午9:42:33
      * @author:chenchen
      */
     @RequestMapping(value = "/getGoodsRecords",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo getGoodsRecords(HttpServletRequest request){
         ResponseParamVo result = null;
         String requestJson = (String) request.getAttribute("requestJson");
         RequestParamVo vo = null;
         try {
             vo = objectMapper.readValue(requestJson, RequestParamVo.class);
             result = transactionMobileService.getGoodsRecords(vo);             
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
     public @ResponseBody ResponseParamVo getGoodsInfoById(HttpServletRequest request){
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
      * @description 商品评论和回复(公用) 
      * @method  saveGoodsComment
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月8日 上午10:39:52
      * @author:chenchen
      */
     @RequestMapping(value = "/saveGoodsComment",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo saveGoodsComment(HttpServletRequest request){
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
      * @date: 2018年4月9日 上午9:44:20
      * @author:chenchen
      */
     @RequestMapping(value = "/saveGoodsCollection",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo saveGoodsCollection(HttpServletRequest request){
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
      * @description 获取首页数据（收藏品） 
      * @method  getHomePageRecords
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月9日 下午2:56:52
      * @author:chenchen
      */
     @RequestMapping(value = "/getHomePageRecords",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo getHomePageRecords(HttpServletRequest request){
    	 ResponseParamVo result = null;
         String requestJson = (String) request.getAttribute("requestJson");
         RequestParamVo vo = null;
         try {
             vo = objectMapper.readValue(requestJson, RequestParamVo.class);
             result = transactionMobileService.getHomePageRecords(vo);             
         } catch (Exception e) {
             return getResponseParamVo(e);
         }
         return result;
     }
     
     /**
      * @description 获取我的收藏品 
      * @method  getMyGoodsRecords
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月12日 下午2:40:51
      * @author:chenchen
      */
     @RequestMapping(value = "/getMyGoodsRecords",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo getMyGoodsRecords(HttpServletRequest request){
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
      * @description 删除收藏品或拍卖 
      * @method  deleteGoodsById
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月12日 下午3:32:44
      * @author:chenchen
      */
     @RequestMapping(value = "/deleteGoodsById",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo deleteGoodsById(HttpServletRequest request){
    	 ResponseParamVo result = null;
         String requestJson = (String) request.getAttribute("requestJson");
         RequestParamVo vo = null;
         try {
             vo = objectMapper.readValue(requestJson, RequestParamVo.class);
             result = transactionMobileService.deleteGoodsById(vo);             
         } catch (Exception e) {
             return getResponseParamVo(e);
         }
         return result;
     }
     
     /**
      * @description 更新收藏品和拍卖上架下架状态 
      * @method  updateGoodsStatusById
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月12日 下午3:32:47
      * @author:chenchen
      */
     @RequestMapping(value = "/updateGoodsStatusById",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo updateGoodsStatusById(HttpServletRequest request){
    	 ResponseParamVo result = null;
         String requestJson = (String) request.getAttribute("requestJson");
         RequestParamVo vo = null;
         try {
             vo = objectMapper.readValue(requestJson, RequestParamVo.class);
             result = transactionMobileService.updateGoodsStatusById(vo);             
         } catch (Exception e) {
             return getResponseParamVo(e);
         }
         return result;
     }
     
     /**
      * @description 我的收藏-藏品 
      * @method  getMyCollectionRecords
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月18日 上午11:19:55
      * @author:chenchen
      */
     @RequestMapping(value = "/getMyCollectionRecords",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo getMyCollectionRecords(HttpServletRequest request){
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
      * @description 取消收藏 
      * @method  deleteCollectGoods
      * @param @param request
      * @param @return
      * @return ResponseParamVo
      * @date: 2018年4月18日 下午4:48:47
      * @author:chenchen
      */
     @RequestMapping(value = "/deleteCollectGoods",method = RequestMethod.POST)
     public @ResponseBody ResponseParamVo deleteCollectGoods(HttpServletRequest request){
    	 ResponseParamVo result = null;
         String requestJson = (String) request.getAttribute("requestJson");
         RequestParamVo vo = null;
         try {
             vo = objectMapper.readValue(requestJson, RequestParamVo.class);
             result = transactionMobileService.deleteCollectGoods(vo);             
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

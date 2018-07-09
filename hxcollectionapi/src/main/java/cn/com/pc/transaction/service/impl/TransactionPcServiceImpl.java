package cn.com.pc.transaction.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.base.util.BaseLogger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.base.constant.BaseConstant;
import cn.com.base.util.StringUtil;
import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamVo;
import cn.com.pc.transaction.dao.TransactionPcMapper;
import cn.com.pc.transaction.service.TransactionPcService;

/**  
 * @类功能说明：藏品pc端serviceImpl
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年4月16日 上午10:22:27 
 * @版本：V1.0  
 */
@Service
public class TransactionPcServiceImpl extends BaseLogger implements TransactionPcService{

	@Autowired
    private BaseConstant baseConstant;
	
	@Autowired
	private TransactionPcMapper transactionPcMapper;
	
	/**
	 * @description 首页藏品广告 
	 * @method  getHomePageAdvertisementRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月16日 上午10:22:06
	 * @author:chenchen
	 */
	public ResponseParamVo getHomePageAdvertisementRecords(RequestParamVo vo) {
		try{			
			JSONObject jsonObj = new JSONObject();          			
            List<Map<String, Object>> advertisementList = transactionPcMapper.getHomePageAdvertisementRecords();                
            jsonObj.put("advertisement_list", JSONArray.fromObject(advertisementList));
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询pc端首页广告成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("查询pc端首页广告异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询pc端首页广告异常","","",new JSONObject());
        }
	}
	
	/**
	 * @description 首页藏品推荐 
	 * @method  getRecommendGoodsRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月16日 下午3:12:45
	 * @author:chenchen
	 */
	public ResponseParamVo getRecommendGoodsRecords(RequestParamVo vo){
		try{			
			JSONObject jsonObj = new JSONObject();                      
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();     
			PageHelper.startPage(Integer.parseInt(bodyMap.get("page_num").toString()), Integer.parseInt(bodyMap.get("page_size").toString()));
            List<Map<String, Object>> recommendGoodsList = transactionPcMapper.getRecommendGoodsRecords();    
            PageInfo<Map<String, Object>> recommendGoods = new PageInfo<>(recommendGoodsList);     
            jsonObj.put("recommend_goods_list", recommendGoods.getList());
            jsonObj.put("total_count", recommendGoods.getTotal());
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询pc端首页藏品推荐成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("查询pc端首页藏品推荐异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询pc端首页藏品推荐异常","","",new JSONObject());
        }
	}
	
	/** 
	 * @description 首页卖家推荐 
	 * @method  getRecommendSellerRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月18日 下午3:56:01
	 * @author:chenchen
	 */
	public ResponseParamVo getRecommendSellerRecords(RequestParamVo vo){
		try{			
			JSONObject jsonObj = new JSONObject();    
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();     
			PageHelper.startPage(Integer.parseInt(bodyMap.get("page_num").toString()), Integer.parseInt(bodyMap.get("page_size").toString()));
			List<Map<String, Object>> recommendSellerList = transactionPcMapper.getRecommendSellerRecords(bodyMap);			
            PageInfo<Map<String, Object>> recommendSeller = new PageInfo<>(recommendSellerList);
            //每个卖家下面查询两个藏品
            PageHelper.startPage(1, 2);
            recommendSellerList = recommendSeller.getList();
            List<Map<String, Object>> goodsList = null;
            Map<String, Object> paramsMap = new HashMap<String, Object>();
            if(recommendSellerList != null && recommendSellerList.size() > 0){
            	for (int i = 0; i < recommendSeller.getList().size(); i++) {
            		paramsMap.put("user_id", recommendSeller.getList().get(i).get("user_id"));
            		goodsList = transactionPcMapper.getMyInSaleGoodsRecords(paramsMap);
            		PageInfo<Map<String, Object>> goods = new PageInfo<>(goodsList);
            		recommendSellerList.get(i).put("goods_list", goods.getList());
				}
            }
            
            jsonObj.put("recommend_seller_list", recommendSellerList);
            jsonObj.put("total_count", recommendSeller.getTotal());
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询pc端首页卖家推荐成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("查询pc端首页卖家推荐异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询pc端首页卖家推荐异常","","",new JSONObject());
        }				
	}
	
	/**
	 * @description 查询收藏品数据 
	 * @method  getGoodsRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月19日 下午3:09:32
	 * @author:chenchen
	 */
	public ResponseParamVo getGoodsRecords(RequestParamVo vo){
		try{			
			JSONObject jsonObj = new JSONObject();
            Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();            
            PageHelper.startPage(Integer.parseInt(bodyMap.get("page_num").toString()), Integer.parseInt(bodyMap.get("page_size").toString()));
            List<Map<String, Object>> goodsList = transactionPcMapper.getGoodsRecords(bodyMap);                     
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(goodsList);
            jsonObj.put("goods_list", pageInfo.getList());
            jsonObj.put("total_count", pageInfo.getTotal());
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询收藏品成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("查询收藏品异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询收藏品异常","","",new JSONObject());
        }
	}
	
	/**
	 * @description 查询本店铺其他藏品 
	 * @method  getMyOtherGoodsRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月23日 上午9:41:54
	 * @author:chenchen
	 */
	public ResponseParamVo getMyOtherGoodsRecords(RequestParamVo vo){
		try{			
			JSONObject jsonObj = new JSONObject();
            Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();            
            PageHelper.startPage(Integer.parseInt(bodyMap.get("page_num").toString()), Integer.parseInt(bodyMap.get("page_size").toString()));
            List<Map<String, Object>> goodsList = transactionPcMapper.getMyOtherGoodsRecords(bodyMap);                     
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(goodsList);
            jsonObj.put("goods_list", pageInfo.getList());
            jsonObj.put("total_count", pageInfo.getTotal());
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询本店铺其他藏品成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("查询本店铺其他藏品异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询本店铺其他藏品异常","","",new JSONObject());
        }
	}
	
	/**
	 * @description 我的足迹 
	 * @method  getMyTrackRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月24日 下午7:34:49
	 * @author:chenchen
	 */
	public ResponseParamVo getMyTrackRecords(RequestParamVo vo){
		try{			
			JSONObject jsonObj = new JSONObject();
            Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();            
            PageHelper.startPage(Integer.parseInt(bodyMap.get("page_num").toString()), Integer.parseInt(bodyMap.get("page_size").toString()));
            List<Map<String, Object>> myTrackList = transactionPcMapper.getMyTrackRecords(bodyMap);                     
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(myTrackList);
            jsonObj.put("track_list", pageInfo.getList());
            jsonObj.put("total_count", pageInfo.getTotal());
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询我的足迹成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("查询我的足迹异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询我的足迹异常","","",new JSONObject());
        }
	}
}

package cn.com.hxfz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hxfz.dao.GoodsDao;
import cn.com.hxfz.dao.ImageRelateDao;
import cn.com.hxfz.model.Goods;
import cn.com.hxfz.model.SysImageRel;
import cn.com.hxfz.service.GoodsService;
import cn.com.hxfz.util.CommUtils;
import cn.com.hxfz.util.StringUtil;

/**
 * 
 * @类功能说明：藏品分类实现类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年4月10日 下午2:22:36 
 * @版本：V1.0
 */
@Service
public class GoodsServiceImpl implements GoodsService {
	private GoodsDao goodsDao;
	public GoodsDao getGoodsDao() {
		return goodsDao;
	}
	@Autowired
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	private ImageRelateDao imageRelateDao;
	public ImageRelateDao getImageRelateDao() {
		return imageRelateDao;
	}
	@Autowired
	public void setImageRelateDao(ImageRelateDao imageRelateDao) {
		this.imageRelateDao = imageRelateDao;
	}
	/**
	 * @description  获取交易帖藏品列表
	 * @method  getGoodsRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Goods>
	 * @date: 2018年4月13日 下午2:06:11
	 * @author:liugui
	 */
	public List<Goods> getGoodsRecords(Map<String, Object> paramsMap){
		// 原始的数据
		List<Goods> goodsList = goodsDao.getGoodsRecords(paramsMap);
		for(int i = 0;i< goodsList.size();i++){
			String cityCode = goodsList.get(i).getCityCode();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("cityCode", cityCode);
			List<Map<String, Object>> parentCode = goodsDao.queryCityParentCode(params);
			if(parentCode.size() > 0){
				String cityParentCode = (String) parentCode.get(0).get("code");
				goodsList.get(i).setCityParentCode(cityParentCode);
			}
		}
		return goodsList;
	}
	/**
	 * 方法功能说明：查询角色数量
	 * 创建：2018年04月01日 by liugui
	 * @return
	 */
	public int getGoodsCount(Map<String, Object> paramsMap){
		return goodsDao.getGoodsCount(paramsMap);
	}
	/**
	 * @description 查看藏品详细信息
	 * @method  getGoodsDetail
	 * @param @param paramsMap
	 * @param @return
	 * @return Goods
	 * @date: 2018年4月13日 下午2:06:49
	 * @author:liugui
	 */
	public Goods getGoodsDetail(Map<String, Object> paramsMap){
		return goodsDao.getGoodsById(paramsMap);
	}
	/**
	 * @description 删除藏品
	 * @method  deletGoodsByIds
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月13日 下午2:07:14
	 * @author:liugui
	 */
	public void deleteGoodsByIds(Map<String, Object> paramsMap){
		// 删除藏品id
		List<String> goodsIdList = new ArrayList<String>();
		// 删除藏品图片id
		List<String> imageIdList = new ArrayList<String>();
		if(paramsMap.get("goodsIds")!=null && paramsMap.get("goodsIds") != ""){
			String ids = paramsMap.get("goodsIds").toString();
			String[] goodsIds = ids.split(",");
			for(String id : goodsIds){
				goodsIdList.add(id);
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("goodsIdList", goodsIdList);
			// 删除藏品分类
			goodsDao.deleteGoodsByIds(params);
		}
		if(paramsMap.get("imageIds")!=null && paramsMap.get("imageIds") != ""){
			String[] imageIds = paramsMap.get("imageIds").toString().split(",");
			for(String imgId : imageIds){
				imageIdList.add(imgId);
			}
			// 删除藏品图片信息
			if(imageIdList.size()>0){
				Map<String, Object> paramsImg = new HashMap<String, Object>();
				paramsImg.put("imageIdList", imageIdList);
				paramsImg.put("serviceIdList", goodsIdList);
				imageRelateDao.deleteImageByIds(paramsImg);
				imageRelateDao.deleteImageClassRel(paramsImg);
			}
		}
	}
	/**
	 * @description 保存藏品信息
	 * @method  saveOrUpdateGoods
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月13日 下午3:58:33
	 * @author:liugui
	 */
	public void saveOrUpdateGoods(Goods goods){
		String goodsId = goods.getGoodsId();
		Integer inventory = goods.getInventory();
		//goodsId不为空,编辑
		if(StringUtil.availableStr(goodsId)){
			System.out.println("需要编辑的藏品信息："+goods);
			goodsDao.updateGoodsById(goods);
		}else{
			goodsId = CommUtils.getUUID();
			inventory = 1;//库存默认为1
			goods.setInventory(inventory);
			goods.setGoodsId(goodsId);
			goodsDao.saveGoods(goods);
		}
		// 先删除藏品图片信息，再新增图片
		// 删除藏品id
		List<String> goodsIdList = new ArrayList<String>();
		goodsIdList.add(goodsId);
		Map<String, Object> paramsImg = new HashMap<String, Object>();
		paramsImg.put("serviceIdList", goodsIdList);
		imageRelateDao.deleteImageGoodsRel(paramsImg);
		//imageIds不为空
		if(StringUtil.availableStr(goods.getImageIds())){
			String[] imageIds = goods.getImageIds().split(",");
			int sort = 1;
			for(int i = 0;i < imageIds.length;i++){
				if(StringUtil.availableStr(imageIds[i])){
					SysImageRel bean = new SysImageRel();
					bean.setId(CommUtils.getUUID());
					bean.setImage_id(imageIds[i]);
					bean.setService_id(goodsId);
					bean.setSort(sort++);
					//新增到系统公共图片和分类关系表
					imageRelateDao.saveImageGoodsRel(bean);
				}
			}
		}
	}
	
	/**
	 * @description 获取评论列表
	 * @method  getGoodsComment
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月16日 下午1:46:57
	 * @author:liugui
	 */
	public List<Map<String,Object>> getGoodsComment(Map<String, Object> paramsMap){
		return goodsDao.getGoodsComment(paramsMap);
	}
	
	/**
	 * @description 更新藏品状态
	 * @method  updateGoodsStatusByIds
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月16日 下午1:57:37
	 * @author:liugui
	 */
	public void updateGoodsStatusByIds(Map<String, Object> paramsMap){
		// 藏品idList
		List<String> goodsIdList = new ArrayList<String>();
		String goodsId = paramsMap.get("goodsId").toString();
		if(StringUtil.availableStr(goodsId)){
			String ids = paramsMap.get("goodsId").toString();
			String[] goodsIds = ids.split(",");
			for(String id : goodsIds){
				goodsIdList.add(id);
			}
			paramsMap.put("goodsIdList", goodsIdList);
			// 更新藏品状态
			goodsDao.updateGoodsStatusByIds(paramsMap);
		}
	}
	
	/**
	 * @description 删除藏品评论
	 * @method  deleteGoodsCommentByIds
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月16日 下午4:57:32
	 * @author:liugui
	 */
	public void deleteGoodsCommentByIds(Map<String, Object> paramsMap){
		// 评论id
		List<String> commentIdList = new ArrayList<String>();
		if(StringUtil.availableStr(paramsMap.get("commentIds").toString())){
			String ids = paramsMap.get("commentIds").toString();
			String[] commentIds = ids.split(",");
			for(String id : commentIds){
				commentIdList.add(id);
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("commentIdList", commentIdList);
			// 删除藏品分类
			goodsDao.deleteGoodsCommentByIds(params);
		}
	}
	
	/**
	 * @description 获取拍卖结拍时间 
	 * @method  getAuctionSession
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月19日 上午10:29:08
	 * @author:liugui
	 */
	public Map<String,Object> getAuctionSession(){
		List<Map<String,Object>> endTimeLists =  goodsDao.getAuctionSession();
		Map<String,Object> resultMap = new HashMap<>();
		if(endTimeLists != null && endTimeLists.size() > 0)
        {
            
            Map<String,Object> endTimeObj = new HashMap<>();
            endTimeObj.put("auction_session",endTimeLists.get(0).get("auction_session"));
            endTimeObj.put("days",endTimeLists.get(0).get("days"));
            resultMap.put("data",endTimeObj);
        }
		return resultMap;
	}
	/**
	 * @description  保存拍卖结拍时间
	 * @method  saveAuctionSession
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月19日 上午10:29:11
	 * @author:liugui
	 */
	public void saveAuctionSession(Map<String, Object> paramsMap){
		//先删除再新增
		goodsDao.deleteAuctionSession();
		if(StringUtil.availableStr(paramsMap.get("auctionSessions").toString())){
			String auctionSession = (String) paramsMap.get("auctionSessions");
			int days = (int) paramsMap.get("days");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", CommUtils.getUUID());
			params.put("auctionSession", auctionSession);
			params.put("days", days);
			// 保存
			goodsDao.saveAuctionSession(params);
		}
	}
	
	/**
	 * @description 获取拍卖藏品出价记录
	 * @method  getGoodsOfferRecord
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月20日 下午4:02:07
	 * @author:liugui
	 */
	public List<Map<String,Object>> getGoodsOfferRecord(Map<String, Object> paramsMap){
		return goodsDao.getGoodsOfferRecord(paramsMap);
	}
	
	/**
	 * @description 删除出价记录
	 * @method  deleteGoodsOfferRecordByIds
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月20日 下午4:07:36
	 * @author:liugui
	 */
	public void deleteGoodsOfferRecordByIds(Map<String, Object> paramsMap){
		// 出价ids
		List<String> offerIdList = new ArrayList<String>();
		if(StringUtil.availableStr(paramsMap.get("offerIds").toString())){
			String ids = paramsMap.get("offerIds").toString();
			String[] offerIds = ids.split(",");
			for(String id : offerIds){
				offerIdList.add(id);
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("idList", offerIdList);
			// 删除藏品出价记录
			goodsDao.deleteGoodsOfferRecordByIds(params);
		}
	}
	
	/**
	 * @description 查询省市信息
	 * @method  queryAreaInfo
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年5月3日 下午2:10:03
	 * @author:liugui
	 */
	public List <Map<String, Object>> queryAreaInfo(){
		
		List<Map<String, Object>> listNew = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listOld = goodsDao.queryAreaInfo();
			
		for (int i = 0; i < listOld.size(); i++) {
			String province = (String) listOld.get(i).get("province");
			String city = (String) listOld.get(i).get("city");
			String parent = (String) listOld.get(i).get("parent");
			listNew.add(listOld.get(i));
			if("1".equals(parent)){
				if(province.equals(city)){
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("code", listOld.get(i).get("code"));
					map.put("city", listOld.get(i).get("city"));
					map.put("parent", listOld.get(i).get("id"));
					listNew.add(map);
				}
			}
		}
		return listNew;
	}
}

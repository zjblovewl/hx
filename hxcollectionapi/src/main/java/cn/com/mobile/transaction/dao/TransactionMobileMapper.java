package cn.com.mobile.transaction.dao;

import java.util.List;
import java.util.Map;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年4月2日 上午9:16:15 
 * @版本：V1.0  
 */
public interface TransactionMobileMapper {
	
	/**
	 * @description 发布收藏品 
	 * @method  publishGoods
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月2日 上午9:58:18
	 * @author:chenchen
	 */
	public void publishGoods(Map<String, Object> paramsMap);
	
	/**
	 * @description 查询收藏品数据 
	 * @method  getGoodsRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月3日 上午11:05:00
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getGoodsRecords(Map<String, Object> paramsMap);
	
	/**
	 * @description 根据图片id查询图片url 
	 * @method  getImageUrlsByIds
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月3日 上午11:05:03
	 * @author:chenchen
	 */
	public Map<String, Object> getImageUrlsByIds(Map<String, Object> paramsMap);
	
	/**
	 * @description 查询收藏品详情 
	 * @method  getGoodsInfoById
	 * @param @param paramsMap
	 * @param @return
	 * @return Map<String,Object>
	 * @date: 2018年4月4日 上午9:38:17
	 * @author:chenchen
	 */
	public Map<String, Object> getGoodsInfoById(Map<String, Object> paramsMap);
	
	/**
	 * @description 根据收藏品id查询卖家个人信息 
	 * @method  getUserInfoByUserId
	 * @param @param paramsMap
	 * @param @return
	 * @return Map<String,Object>
	 * @date: 2018年4月4日 下午2:27:40
	 * @author:chenchen
	 */
	public Map<String, Object> getUserInfoByUserId(Map<String, Object> paramsMap);
	
	/**
	 * @description 我的在售宝贝（收藏品） 
	 * @method  getInSaleOfMyGoods
	 * @param @param paramsMap
	 * @param @return
	 * @return int
	 * @date: 2018年4月4日 下午2:28:36
	 * @author:chenchen
	 */
	public int getInSaleOfMyGoods(Map<String, Object> paramsMap);
	
	/**
	 * @description 我的已售出宝贝（收藏品） 
	 * @method  getOutSaleOfMyGoods
	 * @param @param paramsMap
	 * @param @return
	 * @return int
	 * @date: 2018年4月4日 下午2:29:26
	 * @author:chenchen
	 */
	public int getOutSaleOfMyGoods(Map<String, Object> paramsMap);
	
	/**
	 * @description 查询卖家店铺评论总分 
	 * @method  getTotalScoreOfMyShop
	 * @param @param paramsMap
	 * @param @return
	 * @return int
	 * @date: 2018年4月4日 下午2:32:36
	 * @author:chenchen
	 */
	public int getTotalScoreOfMyShop(Map<String, Object> paramsMap);
	
	/**
	 * @description 查询卖家店铺下的订单总数 
	 * @method  getTotalOrderOfMyShop
	 * @param @param paramsMap
	 * @param @return
	 * @return int
	 * @date: 2018年4月4日 下午2:33:03
	 * @author:chenchen
	 */
	public int getTotalOrderOfMyShop(Map<String, Object> paramsMap);
	
	/**
	 * @description 保存商品评论和回复(公用) 
	 * @method  saveGoodsComment
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月8日 下午2:48:33
	 * @author:chenchen
	 */
	public void saveGoodsComment(Map<String, Object> paramsMap);
	
	/**
	 * @description 收藏收藏品 
	 * @method  saveGoodsCollection
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月9日 上午9:30:37
	 * @author:chenchen
	 */
	public void saveGoodsCollection(Map<String, Object> paramsMap);
	
	/**
	 * @description 收藏品首页广告 
	 * @method  getHomePageAdvertisement
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月9日 下午2:46:31
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getHomePageAdvertisement();
	
	/**
	 * @description 收藏品首页大类分类 
	 * @method  getGoodsClassification
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月9日 下午2:47:16
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getGoodsBigClassification();
	
	/**
	 * @description 收藏品首页列表 
	 * @method  getHomePageGoodsRecords
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月9日 下午2:47:18
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getHomePageGoodsRecords();
	
	/**
	 * @description 获取我的收藏品（在售） 
	 * @method  getMyInSaleGoodsRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月12日 下午2:32:30
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getMyInSaleGoodsRecords(Map<String, Object> paramsMap);
	
	/**
	 * @description 获取我的收藏品（已下架） 
	 * @method  getMyOffShelfGoodsRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月12日 下午2:32:47
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getMyOffShelfGoodsRecords(Map<String, Object> paramsMap);
	
	/**
	 * @description 获取我的收藏品（已售出） 
	 * @method  getMyOutSaleGoodsRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月12日 下午2:32:50
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getMyOutSaleGoodsRecords(Map<String, Object> paramsMap);
	
	/**
	 * @description 删除收藏品或拍卖 
	 * @method  deleteGoodsById
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月12日 下午3:16:44
	 * @author:chenchen
	 */
	public void deleteGoodsById(Map<String, Object> paramsMap);
	
	/**
	 * @description 更新收藏品和拍卖上架下架状态 
	 * @method  updateGoodsStatusById
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月12日 下午3:17:00
	 * @author:chenchen
	 */
	public void updateGoodsStatusById(Map<String, Object> paramsMap);
	
	/**
	 * @description 我的收藏-藏品 
	 * @method  getMyCollectionRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月18日 上午11:16:10
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getMyCollectionRecords(Map<String, Object> paramsMap);
	
	/**
	 * @description 取消收藏 
	 * @method  deleteCollectGoods
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月18日 下午4:46:53
	 * @author:chenchen
	 */
	public void deleteCollectGoods(Map<String, Object> paramsMap);
	
	/**
	 * @description 根据藏品id查询藏品 
	 * @method  getCollectionGoodsById
	 * @param @param paramsMap
	 * @param @return
	 * @return Map<String,Object>
	 * @date: 2018年4月19日 下午4:50:53
	 * @author:chenchen
	 */
	public Map<String, Object> getCollectionGoodsById(Map<String, Object> paramsMap);
	
	/**
	 * @description 保存藏品浏览数+1 
	 * @method  saveGoodsBrowseNum
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月24日 下午5:18:13
	 * @author:chenchen
	 */
	public void saveGoodsBrowseNum(Map<String, Object> paramsMap);
}

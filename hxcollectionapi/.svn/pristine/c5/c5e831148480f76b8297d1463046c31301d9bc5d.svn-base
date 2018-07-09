package cn.com.mobile.order.dao;

import java.util.List;
import java.util.Map;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年5月7日 下午3:19:56 
 * @版本：V1.0  
 */
public interface OrderMobileMapper {
	/**
	 * @description 创建订单
	 * @method  saveOrder
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年5月8日 上午10:11:25
	 * @author:chenchen
	 */
	public void saveOrder(Map<String, Object> paramsMap);
	
	/**	 
	 * @description 创建订单商品数据
	 * @method  saveOrderGoods
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年5月8日 上午10:12:27
	 * @author:chenchen
	 */
	public void saveOrderGoods(Map<String, Object> paramsMap);
	
	/**	 
	 * @description 保存订单商品图片数据
	 * @method  saveImagesOrderGoodsRel
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年5月8日 上午10:42:33
	 * @author:chenchen
	 */
	public void saveImagesOrderGoodsRel(Map<String, Object> paramsMap);
	
	/**
	 * @description 售出记录（已完成的订单）
	 * @method  getSellOrderRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年5月9日 上午11:29:32
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getSellOrderRecords(Map<String, Object> paramsMap);
	
	/**
	 * @description 购买记录（已完成的订单）
	 * @method  getBuyOrderRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年5月9日 上午11:29:45
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getBuyOrderRecords(Map<String, Object> paramsMap);
	
	/**
	 * @description 查询订单评论
	 * @method  getOrderEvaluateRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年5月9日 上午11:30:14
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getOrderEvaluateRecords(Map<String, Object> paramsMap);
	
	/**
	 * @description 我售出的
	 * @method  getMySellOrderRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年5月10日 上午9:56:26
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getMySellOrderRecords(Map<String, Object> paramsMap);
	
	/**
	 * @description 我购买的
	 * @method  getMyBuyOrderRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年5月10日 上午9:56:29
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getMyBuyOrderRecords(Map<String, Object> paramsMap);
	
	/**
	 * @description 查询订单详情
	 * @method  getOrderInfoById
	 * @param @param paramsMap
	 * @param @return
	 * @return Map<String,Object>
	 * @date: 2018年5月15日 下午4:03:42
	 * @author:chenchen
	 */
	public Map<String, Object> getOrderInfoById(Map<String, Object> paramsMap);
	
	/**
	 * @description 发货
	 * @method  sendOutGoods
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年5月15日 下午5:33:34
	 * @author:chenchen
	 */
	public void sendOutGoods(Map<String, Object> paramsMap);
	
	/**
	 * @description 订单评价
	 * @method  saveOrderEvaluate
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年5月16日 下午2:05:28
	 * @author:chenchen
	 */
	public void saveOrderEvaluate(Map<String, Object> paramsMap);
	
	/**
	 * @description 取消订单 
	 * @method  cancelOrder
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年5月16日 下午5:02:21
	 * @author:chenchen
	 */
	public void cancelOrder(Map<String, Object> paramsMap);
	
	/**
	 * @description 确认收货
	 * @method  confirmCollectGoods
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年5月16日 下午5:33:23
	 * @author:chenchen
	 */
	public void confirmCollectGoods(Map<String, Object> paramsMap);
	
	/**
	 * @description 申请退款 
	 * @method  applyRefund
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年5月17日 上午9:34:37
	 * @author:chenchen
	 */
	public void applyRefund(Map<String, Object> paramsMap);
	
	/**
	 * @description 同意退款 
	 * @method  agreeRefund
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年5月17日 上午9:53:20
	 * @author:chenchen
	 */
	public void agreeRefund(Map<String, Object> paramsMap);
	
	/**
	 * @description 根据订单号更新订单状态
	 * @method  updateOrderStatusByOrderCode
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年5月25日 上午11:15:30
	 * @author:chenchen
	 */
	public void updateOrderStatusByOrderCode(Map<String, Object> paramsMap);
	
	/**
	 * @description 根据订单状态查询订单信息 
	 * @method  getOrderRecordsByStatus
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年5月30日 下午3:49:17
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getOrderRecordsByStatus(Map<String, Object> paramsMap);	
	
	/**
	 * @description 查询待支付订单,超时未付款(超时30分钟)
	 * @method  getOvertimeUnPayOrderRecords
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年5月30日 下午5:34:27
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getOvertimeUnPayOrderRecords();
	
	/**
	 * @description 查询待收货订单,超时未确认收货(超时10天)
	 * @method  getOvertimeUnReceiveOrderRecords
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年5月30日 下午5:34:38
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getOvertimeUnReceiveOrderRecords();
	
	/**
	 * @description 批量取消订单
	 * @method  batchCancelOrder	 
	 * @return void
	 * @date: 2018年5月30日 下午5:53:25
	 * @author:chenchen
	 */
	public void batchCancelOrder();
	
	/**
	 * @description 批量确认收货订单
	 * @method  batchCancelOrder
	 * @return void
	 * @date: 2018年5月30日 下午5:53:25
	 * @author:chenchen
	 */
	public void batchConfirmCollectGoods();
	
	/**
	 * @description 根据订单id查询商品库存信息 
	 * @method  getGoodsInventoryByOrderId
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年5月31日 上午9:34:54
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getGoodsInventoryByOrderId(Map<String, Object> paramsMap);

	/**
	 * @description 支付成功减库存
	 * @method  updateGoodsInventory
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年5月31日 上午9:35:32
	 * @author:chenchen
	 */
	public void updateGoodsInventory(Map<String, Object> paramsMap);
} 

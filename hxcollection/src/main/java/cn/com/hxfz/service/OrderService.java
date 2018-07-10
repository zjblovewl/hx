package cn.com.hxfz.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * @类功能说明：交易帖(藏品) 业务接口
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年4月12日 下午8:31:36 
 * @版本：V1.0
 */
public interface OrderService {
	/**
	 * 
	 * @description 获取订单记录
	 * @method  getOrderRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Order>
	 * @date: 2018年5月21日 上午10:55:45
	 * @author:liugui
	 */
	public List<Map<String, Object>> getOrderRecords(Map<String, Object> param);
	/**
	 * 
	 * @description 获取订单数量
	 * @method  getOrderCount
	 * @param @param paramsMap
	 * @param @return
	 * @return int
	 * @date: 2018年5月21日 上午10:55:27
	 * @author:liugui
	 */
	public int getOrderCount(Map<String, Object> paramsMap);

	/**
	 * 
	 * @description： 编辑订单收货地址
	 * @method:  saveOrder
	 * @param:   @param paramsMap
	 * @return:  void
	 * @date:    2018年5月24日 上午10:00:14 by qiangxuan
	 */
	public void saveOrder(Map<String, Object> paramsMap);
	
	/**
	 * 
	 * @description： 导出订单信息至excel
	 * @method:  getExportOrderList
	 * @param:   @param params
	 * @param:   @return
	 * @return:  List<Map<String,Object>>
	 * @date:    2018年5月24日 下午4:06:16 by qiangxuan
	 */
	public List<Map<String, Object>> getExportOrderList(
			Map<String, Object> params);
}

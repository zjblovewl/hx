package cn.com.hxfz.dao;

import java.util.List;
import java.util.Map;

public interface OrderDao {
	/**
	 * 
	 * @description 获取订单记录
	 * @method  getOrderRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Order>
	 * @date: 2018年5月21日 上午10:53:29
	 * @author:liugui
	 */
	public List<Map<String, Object>> getOrderRecords(Map<String, Object> paramsMap);
	/**
	 * 
	 * @description 获取订单数量
	 * @method  getOrderCount
	 * @param @param paramsMap
	 * @param @return
	 * @return int
	 * @date: 2018年5月21日 上午10:53:32
	 * @author:liugui
	 */
	public int getOrderCount(Map<String, Object> paramsMap);
	
	/**
	 * 
	 * @description： 编辑订单收货地址根据ID
	 * @method:  updateOrderById
	 * @param:   @param paramsMap
	 * @return:  void
	 * @date:    2018年5月24日 上午10:01:54 by qiangxuan
	 */
	public void updateOrderById(Map<String, Object> paramsMap);
	
	/**
	 * 
	 * @description： 导出订单记录至excel
	 * @method:  exportOrderRecord
	 * @param:   @param params
	 * @param:   @return
	 * @return:  List<Map<String,Object>>
	 * @date:    2018年5月24日 下午4:07:45 by qiangxuan
	 */
	public List<Map<String, Object>> exportOrderRecord(
			Map<String, Object> params);
}
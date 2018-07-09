package cn.com.mobile.order.service;

import java.util.List;
import java.util.Map;

import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamVo;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年5月7日 下午3:20:37 
 * @版本：V1.0  
 */
public interface OrderMobileService {
	/**	 
	 * @description 创建订单 
	 * @method  saveOrder
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月8日 上午10:52:08
	 * @author:chenchen
	 */
	public ResponseParamVo saveOrder(RequestParamVo vo);
	
	/**
	 * @description 售出记录（已完成的订单）
	 * @method  getSellOrderRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月9日 下午2:49:12
	 * @author:chenchen
	 */
	public ResponseParamVo getSellOrderRecords(RequestParamVo vo);
	
	/**
	 * @description 购买记录（已完成的订单）
	 * @method  getBuyOrderRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月9日 下午2:49:19
	 * @author:chenchen
	 */
	public ResponseParamVo getBuyOrderRecords(RequestParamVo vo);
	
	/**
	 * @description 我售出的
	 * @method  getMySellOrderRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月10日 上午10:06:44
	 * @author:chenchen
	 */
	public ResponseParamVo getMySellOrderRecords(RequestParamVo vo);
	
	/**
	 * @description 我购买的
	 * @method  getMyBuyOrderRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月10日 上午10:07:07
	 * @author:chenchen
	 */
	public ResponseParamVo getMyBuyOrderRecords(RequestParamVo vo);
	
	/**
	 * @description 查询订单详情
	 * @method  getOrderInfoById
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月15日 下午4:04:19
	 * @author:chenchen
	 */
	public ResponseParamVo getOrderInfoById(RequestParamVo vo);
	
	/**
	 * @description 发货 
	 * @method  sendOutGoods
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月15日 下午5:35:42
	 * @author:chenchen
	 */
	public ResponseParamVo sendOutGoods(RequestParamVo vo);
		
	/**
	 * @description 订单评价 
	 * @method  saveOrderEvaluate
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月16日 下午2:06:50
	 * @author:chenchen
	 */
	public ResponseParamVo saveOrderEvaluate(RequestParamVo vo);
	
	/**
	 * @description 取消订单 
	 * @method  cancelOrder
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月16日 下午5:10:51
	 * @author:chenchen
	 */
	public ResponseParamVo cancelOrder(RequestParamVo vo);
	
	/**
	 * @description 确认收货 
	 * @method  confirmCollectGoods
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月16日 下午5:34:01
	 * @author:chenchen
	 */
	public ResponseParamVo confirmCollectGoods(RequestParamVo vo);
	
	/**
	 * @description 申请退款
	 * @method  applyRefund
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月17日 上午9:35:11
	 * @author:chenchen
	 */
	public ResponseParamVo applyRefund(RequestParamVo vo);
	
	/**
	 * @description 同意退款 
	 * @method  agreeRefund
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月17日 上午9:54:05
	 * @author:chenchen
	 */
	public ResponseParamVo agreeRefund(RequestParamVo vo);
	
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
}

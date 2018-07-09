package cn.com.third.ping.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamVo;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：nanrui  
 * @创建时间：2018年6月1日 下午3:20:37 
 * @版本：V1.0  
 */
public interface ChargeOrdersService {
	/**	 
	 * @description 支付订单获取预付码 
	 * @method  getPrepaymentCodeById
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年6月1日  15:52:08
	 * @author:nanrui
	 */
	public ResponseParamVo getPrepaymentCodeById(RequestParamVo vo);
	/**
     * @description 第三方支付异步回调 
     * @method  payBack
     * @param @param request
     * @param @return
     * @return ResponseParamVo
     * @date: 2018年6月12日 上午11:01:04
     * @author:nanrui
     */
	public int payBack(HttpServletRequest request);
}

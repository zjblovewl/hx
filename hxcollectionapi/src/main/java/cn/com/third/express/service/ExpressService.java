package cn.com.third.express.service;

import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamVo;

/**
 * @类功能说明：快递100物流信息业务层
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/5/9 上午10:08
 * @版本：V1.0
 */
public interface ExpressService {
	/**
	 * @description 获取物流信息 
	 * @method  getExpressInfo
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年6月19日 上午11:07:57
	 * @author:nanrui
	 */
	public ResponseParamVo getExpressInfo(RequestParamVo vo);
}

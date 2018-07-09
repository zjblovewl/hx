package cn.com.mobile.message.service;

import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamVo;

/**  
 * @类功能说明：我的消息service
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年4月13日 上午9:36:50 
 * @版本：V1.0  
 */
public interface MessageMobileService {
	/**
	 * @description 获取我的消息记录
	 * @method  getMyMessageRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月13日 下午2:51:57
	 * @author:chenchen
	 */
	public ResponseParamVo getMyMessageRecords(RequestParamVo vo);
	
	/**
	 * @description 我的消息-留言 
	 * @method  getLeavingMessageRecordsById
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月16日 上午9:22:13
	 * @author:chenchen
	 */
	public ResponseParamVo getLeavingMessageRecordsById(RequestParamVo vo);
	
	/**
	 * @description 我的消息-系统消息 
	 * @method  getSysMessageRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月16日 上午9:22:22
	 * @author:chenchen
	 */
	public ResponseParamVo getSysMessageRecords(RequestParamVo vo);
	
	/** 
	 * @description 查看公告详情 
	 * @method  getMessageById
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月23日 下午5:01:27
	 * @author:chenchen
	 */
	public ResponseParamVo getMessageById(RequestParamVo vo);
}

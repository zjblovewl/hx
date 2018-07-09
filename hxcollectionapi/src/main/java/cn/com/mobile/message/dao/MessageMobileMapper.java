package cn.com.mobile.message.dao;

import java.util.List;
import java.util.Map;

/**  
 * @类功能说明：我的消息mapper
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年4月13日 上午9:37:32 
 * @版本：V1.0  
 */
public interface MessageMobileMapper {
	/**
	 * @description 我的留言 
	 * @method  getMyGoodsCommentRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月13日 下午2:47:15
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getMyGoodsCommentRecords(Map<String, Object> paramsMap);
	
	/**
	 * @description 系统消息 
	 * @method  getSysMessageRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月13日 下午2:48:51
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getSysMessageRecords(Map<String, Object> paramsMap);
	
	/**
	 * @description 查看公告详情 
	 * @method  getMessageById
	 * @param @param paramsMap
	 * @param @return
	 * @return Map<String,Object>
	 * @date: 2018年4月23日 下午4:59:44
	 * @author:chenchen
	 */
	public Map<String, Object> getMessageById(Map<String, Object> paramsMap);
}

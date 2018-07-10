package cn.com.hxfz.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hxfz.dao.SysMessageDao;
import cn.com.hxfz.service.SysMessageService;
import cn.com.hxfz.util.CommUtils;
import cn.com.hxfz.util.StringUtil;
import cn.com.hxfz.util.StringUtils;

/**
 * 
 * @类功能说明：系统消息业务实现类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年4月9日 下午2:51:13 
 * @版本：V1.0
 */
@Service("sysMessageService")
public class SysMessageServiceImpl implements SysMessageService {

	private SysMessageDao messageDao;
	public SysMessageDao getMessageDao() {
		return messageDao;
	}
	@Autowired
	public void setMessageDao(SysMessageDao messageDao) {
		this.messageDao = messageDao;
	}
	/**
	 * 
	 * @description 查询系统消息列表
	 * @method  getMessageRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月9日 下午2:50:31
	 * @author:liugui
	 */
	public List<Map<String, Object>> getMessageRecords(Map<String, Object> paramsMap){
		return messageDao.getMessageRecords(paramsMap);
	}
	
	/**
	 * 
	 * @description 查询系统消息总数
	 * @method  getDictionaryCount
	 * @param @return
	 * @return int
	 * @date: 2018年4月9日 下午2:55:16
	 * @author:liugui
	 */
	public int getMessageCount(Map<String, Object> paramsMap){
		return messageDao.getMessageCount(paramsMap);
	}
	
	/**
	 * 
	 * @description 查询消息详细信息
	 * @method  getMessageDetail
	 * @param @param paramsMap
	 * @param @return
	 * @return Dictionary
	 * @date: 2018年4月9日 下午5:40:57
	 * @author:liugui
	 */
	public List<Map<String, Object>> getMessageDetail(Map<String, Object> paramsMap){
		return messageDao.getMessageDetail(paramsMap);
	}
	/**
	 * 
	 * @description 保存消息信息
	 * @method  saveOrUpdateMessage
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月9日 下午5:41:17
	 * @author:liugui
	 */
	public void saveOrUpdateMessage(Map<String, Object> paramsMap){
		//ID不为空，编辑
		if(StringUtil.availableStr(paramsMap.get("mesId").toString())){
			messageDao.updateMessageById(paramsMap);
		}else{
			String mesId = CommUtils.getUUID();
			paramsMap.put("mesId", mesId);
			messageDao.saveMessage(paramsMap);
		}
	}
	/**
	 * 
	 * @description 删除字消息信息
	 * @method  deleteMessageByIds
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月9日 下午5:41:55
	 * @author:liugui
	 */
	public void deleteMessageByIds(Map<String, Object> paramsMap){
		String ids = paramsMap.get("mesIds").toString();
		if(StringUtils.isNotEmpty(ids)){
			String[] mesIds = ids.split(",");
			List<String> idList = Arrays.asList(mesIds);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("mesIdList", idList);
			messageDao.deleteMessageByIds(params);
		}
		
	}
}

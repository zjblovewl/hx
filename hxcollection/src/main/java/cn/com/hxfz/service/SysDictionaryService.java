package cn.com.hxfz.service;

import java.util.List;
import java.util.Map;

import cn.com.hxfz.model.Dictionary;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年3月30日 上午下午3:23:38 
 * @版本：V1.0  
 */
public interface SysDictionaryService {
	/**
	 * 
	 * @description 查询字典列表
	 * @method  getDictionaryRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年3月30日 下午3:09:49
	 * @author:liugui
	 */
	public List<Map<String, Object>> getDictionaryRecords(Map<String, Object> paramsMap);
	/**
	 * 
	 * @description 查询字典总数
	 * @method  getDictionaryCount
	 * @param @return
	 * @return int
	 * @date: 2018年3月30日 下午3:11:24
	 * @author:liugui
	 */
	public int getDictionaryCount();
	/**
	 * 
	 * @description 查询字典详细信息
	 * @method  getDictionaryDetail
	 * @param @param paramsMap
	 * @param @return
	 * @return Map<String,Object>
	 * @date: 2018年3月30日 下午3:11:28
	 * @author:liugui
	 */
	public Dictionary getDictionaryDetail(Map<String, Object> paramsMap);
	/**
	 * 
	 * @description 保存字典信息
	 * @method  saveDictionary
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年3月30日 下午3:11:34
	 * @author:liugui
	 */
	public void saveOrUpdateDictionary(Map<String, Object> paramsMap);
	/**
	 * 
	 * @description 删除字典信息
	 * @method  deleteDictionaryByIds
	 * @param @param dicIdList
	 * @return void
	 * @date: 2018年3月30日 下午3:15:11
	 * @author:liugui
	 */
	public void deleteDictionaryByIds(Map<String, Object> paramsMap);
	
	/**
	 * 
	 * @description 查询字典键值对列表
	 * @method  getKeyAndValueRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月9日 上午10:14:41
	 * @author:liugui
	 */
	public List<Map<String, Object>> getKeyAndValueRecords(Map<String, Object> paramsMap);
	/**
	 * 
	 * @description 保存字典键值对信息
	 * @method  saveOrUpdateKeyAndVal
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月9日 上午10:26:15
	 * @author:liugui
	 */
	public void saveOrUpdateKeyAndVal(Map<String, Object> paramsMap);
	
	/**
	 * @description 根据字典key获取数据字典列表
	 * @method  getDicListByDicKey
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月16日 下午5:51:46
	 * @author:liugui
	 */
	public List<Map<String, Object>> getDicListByDicKey(Map<String, Object> paramsMap);
	/**
	 * @description  校验字典CODE是否已存在
	 * @method  checkDicCode
	 * @param @param paramsMap
	 * @param @return
	 * @return Boolean
	 * @date: 2018年4月24日 下午8:29:17
	 * @author:liugui
	 */
	public Boolean checkDicCode(Map<String, Object> paramsMap);
}

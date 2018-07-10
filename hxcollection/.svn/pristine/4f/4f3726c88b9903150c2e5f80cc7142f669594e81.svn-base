package cn.com.hxfz.dao;

import java.util.List;
import java.util.Map;

import cn.com.hxfz.model.Dictionary;

/**  
 * @类功能说明：数据字典数据访问接口
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年3月30日 上午下午3:09:03 
 * @版本：V1.0  
 */
public interface DictionaryDao {

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
	public void saveDictionary(Map<String, Object> paramsMap);
	/**
	 * 
	 * @description 根绝字典id更新信息
	 * @method  updateDictionaryById
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年3月30日 下午3:15:07
	 * @author:liugui
	 */
	public void updateDictionaryById(Map<String, Object> paramsMap);
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
	 * @method  getKeyAndValRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月9日 上午10:09:57
	 * @author:liugui
	 */
	public List<Map<String, Object>> getKeyAndValRecords(Map<String, Object> paramsMap);
	
	/**
	 * 
	 * @description 保存字典键值对信息
	 * @method  saveKeyAndVal
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月9日 上午10:27:43
	 * @author:liugui
	 */
	public void saveKeyAndVal(Map<String, Object> paramsMap);
	
	/**
	 * 
	 * @description 根绝字典id更新信息
	 * @method  updateKeyAndValById
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月9日 上午10:29:55
	 * @author:liugui
	 */
	public void updateKeyAndValById(Map<String, Object> paramsMap);
	
	/**
	 * @description 根据字典key获取数据字典列表
	 * @method  getDicListByDicKey
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月16日 下午5:53:22
	 * @author:liugui
	 */
	public List<Map<String, Object>> getDicListByDicKey(Map<String, Object> paramsMap);
	
	/**
	 * @description 新增校验  字典CODE是否已存在
	 * @method  checkAddDicValue
	 * @param @param paramsMap
	 * @param @return
	 * @return int
	 * @date: 2018年4月24日 下午8:31:56
	 * @author:liugui
	 */
	public int checkAddDicValue(Map<String, Object> paramsMap);
	/**
	 * @description 编辑校验  字典CODE是否已存在
	 * @method  checkEditDicValue
	 * @param @param paramsMap
	 * @param @return
	 * @return int
	 * @date: 2018年4月24日 下午8:31:53
	 * @author:liugui
	 */
	public int checkEditDicValue(Map<String, Object> paramsMap);
}

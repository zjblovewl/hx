package cn.com.hxfz.service;

import java.util.List;
import java.util.Map;

/**  
 * @类功能说明：佣金记录接口
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月25日 上午10:42:03 
 * @版本：V1.0  
 */
public interface CommissionRecordService {
	

	public List<Map<String, Object>> getCommissionList(Map<String, Object> param);
	
	int getCommissionListCount(Map<String, Object> param);

	public List<Map<String, Object>> getExportCommissionList(
			Map<String, Object> params);

	public void saveCommissionConfig(Map<String, Object> paramsMap);

	public List<Map<String, Object>> getCRList(Map<String, Object> param);
	
}

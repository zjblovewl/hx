package cn.com.hxfz.service;

import java.util.List;
import java.util.Map;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月3日 下午2:57:11 
 * @版本：V1.0  
 */
public interface SellerComplaintService {
	
	List<Map<String, Object>> getSellerComplaintList(Map<String, Object> param);

	int getSellerComplaintCount(Map<String, Object> param);

	void deleteSC(Map<String, Object> paramsMap);

	void delMoreSC(Map<String, Object> paramsMap);

	void saveScInfo(Map<String, Object> paramsMap);
}

package cn.com.hxfz.dao;

import java.util.List;
import java.util.Map;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月3日 下午2:54:39 
 * @版本：V1.0  
 */
public interface SellerComplaintDao {
	
	List<Map<String, Object>> getSellerComplaintList(Map<String, Object> param);

	int getSellerComplaintCount(Map<String, Object> param);

	void deleteSCById(Map<String, Object> paramsMap);

	void delMoreSC(Map<String, Object> paramsMap);

	void updateSellerComInfoById(Map<String, Object> paramsMap);
}

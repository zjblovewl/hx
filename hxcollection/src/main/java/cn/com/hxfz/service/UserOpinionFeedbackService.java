package cn.com.hxfz.service;

import java.util.List;
import java.util.Map;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年3月29日 上午10:28:19 
 * @版本：V1.0  
 */
public interface UserOpinionFeedbackService {

	List<Map<String, Object>> getUserOpinionFeedbackList(Map<String, Object> param);

	int getUserOpinionFeedbackCount(Map<String, Object> param);

	void deleteOpinionFeedback(Map<String, Object> paramsMap);

	void saveUserFC(Map<String, Object> paramsMap);

}

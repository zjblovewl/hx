package cn.com.hxfz.service;

import java.util.List;
import java.util.Map;

import cn.com.hxfz.util.mybatise.DataSource;

/**
 * 
 * @公司名称: 南京华讯方舟通讯设备有限公司
 * @项目名称: hxcollection
 * @类路径: OperationLogService
 * @作者: qiangxuan
 * @时间: 2018年3月23日 下午3:31:54
 * @版本: V1.0
 */
public interface OperationLogService {
	
//	/**
//	 * 
//	 * 方法功能说明：
//	 * 创建：2018年3月23日 by qiangxuan
//	 * 返回类型：Map<String,Object>      
//	 * @param param
//	 * @return
//	 */
//    @DataSource("master")
//	Map<String, Object> getOperationLog(Map<String, Object> param);
    
    /**
     * 
     * 方法功能说明： 获取操作日志数量
     * 创建：2018年3月27日 by qiangxuan
     * 返回类型：int      
     * @param param
     * @return
     */
    @DataSource("master")
	int getOperationLogCount(Map<String, Object> param);
    
    /**
     * 获取操作日志
     * 方法功能说明： TODO 
     * 创建：2018年3月27日 by qiangxuan
     * 返回类型：List<Map<String,Object>>      
     * @param param
     * @return
     */
    @DataSource("master")
	public List<Map<String, Object>> getOperationLogList(Map<String, Object> param);
	
}

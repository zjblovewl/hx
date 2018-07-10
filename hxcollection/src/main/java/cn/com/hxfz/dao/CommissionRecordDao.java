package cn.com.hxfz.dao;

import java.util.List;
import java.util.Map;

/**  
 * @类功能说明：佣金记录数据访问接口
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月25日 上午10:44:15 
 * @版本：V1.0  
 */
public interface CommissionRecordDao {
	/**
	 * 
	 * @description： 获取佣金列表
	 * @method:  queryCommissionList
	 * @param:   @param param
	 * @param:   @return
	 * @return:  List<Map<String,Object>>
	 * @date:    2018年6月28日 下午4:34:04 by qiangxuan
	 */
	List<Map<String, Object>> queryCommissionList(Map<String, Object> param);
	/**
	 * 
	 * @description： 获取佣金总数
	 * @method:  queryCommissionCount
	 * @param:   @param param
	 * @param:   @return
	 * @return:  int
	 * @date:    2018年6月28日 下午4:34:10 by qiangxuan
	 */
	int queryCommissionCount(Map<String, Object> param);
	/**
	 * 
	 * @description： 获取需导出佣金记录列表
	 * @method:  exportCommissionList
	 * @param:   @param params
	 * @param:   @return
	 * @return:  List<Map<String,Object>>
	 * @date:    2018年6月28日 下午4:34:15 by qiangxuan
	 */
	List<Map<String, Object>> exportCommissionList(Map<String, Object> params);
	/**
	 * 
	 * @description： 新增佣金配置
	 * @method:  addCommissionRate
	 * @param:   @param paramsMap
	 * @return:  void
	 * @date:    2018年6月28日 下午4:34:20 by qiangxuan
	 */
	void addCommissionRate(Map<String, Object> paramsMap);
	/**
	 * 
	 * @description： 编辑佣金配置
	 * @method:  updateCommissionRate
	 * @param:   @param paramsMap
	 * @return:  void
	 * @date:    2018年6月28日 下午4:34:25 by qiangxuan
	 */
	void updateCommissionRate(Map<String, Object> paramsMap);
	/**
	 * 
	 * @description： 查询佣金配置总数
	 * @method:  queryCommissionRate
	 * @param:   @param paramsMap
	 * @param:   @return
	 * @return:  int
	 * @date:    2018年6月28日 下午4:34:32 by qiangxuan
	 */
	int queryCommissionRate(Map<String, Object> paramsMap);
	/**
	 * 
	 * @description： 
	 * @method:  queryCRList
	 * @param:   @param param
	 * @param:   @return
	 * @return:  List<Map<String,Object>>
	 * @date:    2018年6月28日 下午4:34:39 by qiangxuan
	 */
	List<Map<String, Object>> queryCRList(Map<String, Object> param);
	
}

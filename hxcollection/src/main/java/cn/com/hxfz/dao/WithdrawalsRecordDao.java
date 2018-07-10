package cn.com.hxfz.dao;

import java.util.List;
import java.util.Map;

/**  
 * @类功能说明：提现管理数据访问层接口类 
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月21日 下午2:34:13 
 * @版本：V1.0  
 */
public interface WithdrawalsRecordDao {
	/**
	 * 
	 * @description： 获取提现记录数据
	 * @method:  queryWRList
	 * @param:   @param param
	 * @param:   @return
	 * @return:  List<Map<String,Object>>
	 * @date:    2018年5月21日 下午3:27:12 by qiangxuan
	 */
	List<Map<String, Object>> queryWRList(Map<String, Object> param);
	/**
	 * 
	 * @description： 获取提现记录总数
	 * @method:  queryWRCount
	 * @param:   @param param
	 * @param:   @return
	 * @return:  int
	 * @date:    2018年5月21日 下午3:27:18 by qiangxuan
	 */
	int queryWRCount(Map<String, Object> param);
	/**
	 * 
	 * @description： 根据搜索条件获取提现记录并导出至excel
	 * @method:  exportWRList
	 * @param:   @param params
	 * @param:   @return
	 * @return:  List<Map<String,Object>>
	 * @date:    2018年5月21日 下午3:27:21 by qiangxuan
	 */
	List<Map<String, Object>> exportWRList(Map<String, Object> params);
	/**
	 * 
	 * @description： 根据ID编辑提现记录审核状态
	 * @method:  updateWRAuditById
	 * @param:   @param param
	 * @return:  void
	 * @date:    2018年5月22日 下午3:07:26 by qiangxuan
	 */
	void updateWRAuditById(Map<String, Object> param);
	
}

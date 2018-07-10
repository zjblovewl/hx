package cn.com.hxfz.service;

import java.util.List;
import java.util.Map;

/**  
 * @类功能说明：提现管理业务接口
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月21日 下午2:34:01 
 * @版本：V1.0  
 */
public interface WithdrawalsRecordService {
	
	/**
	 * 
	 * @description： 获取提现记录所有数据
	 * @method:  getWRList
	 * @param:   @param param
	 * @param:   @return
	 * @return:  List<Map<String,Object>>
	 * @date:    2018年5月21日 下午3:21:33 by qiangxuan
	 */
	public List<Map<String, Object>> getWRList(Map<String, Object> param);
	/**
	 * 
	 * @description：  获取提现记录总数
	 * @method:  getWRListCount
	 * @param:   @param param
	 * @param:   @return
	 * @return:  int
	 * @date:    2018年5月21日 下午3:21:45 by qiangxuan
	 */
	int getWRListCount(Map<String, Object> param);
	/**
	 * 
	 * @description： 根据搜索条件查询提现记录数据并导出至EXCEL
	 * @method:  getExportWRList
	 * @param:   @param params
	 * @param:   @return
	 * @return:  List<Map<String,Object>>
	 * @date:    2018年5月21日 下午3:21:51 by qiangxuan
	 */
	public List<Map<String, Object>> getExportWRList(
			Map<String, Object> params);
	/**
	 * 
	 * @description： 根据ID编辑提现审核状态
	 * @method:  updateWRAuditStatus
	 * @param:   @param param
	 * @return:  void
	 * @date:    2018年5月22日 下午3:05:08 by qiangxuan
	 */
	void updateWRAuditStatus(Map<String, Object> param);
}

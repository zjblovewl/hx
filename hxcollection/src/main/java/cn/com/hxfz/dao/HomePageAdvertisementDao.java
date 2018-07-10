package cn.com.hxfz.dao;

import java.util.List;
import java.util.Map;

import cn.com.hxfz.model.HomePageAdvertisement;
/**
 * 
 * @类功能说明：藏城首页广告表
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年4月23日 上午9:38:21 
 * @版本：V1.0
 */
public interface HomePageAdvertisementDao {
	/**
	 * @description 获取藏城首页广告
	 * @method  getHomePageAdvertisementRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<HomePageAdvertisement>
	 * @date: 2018年4月23日 上午10:02:31
	 * @author:liugui
	 */
	public List<HomePageAdvertisement> getHomePageAdvertisementRecords(Map<String, Object> paramsMap);
	/**
	 * @description 获取广告数量
	 * @method  getHomePageAdvertisementCount
	 * @param @param paramsMap
	 * @param @return
	 * @return int
	 * @date: 2018年4月23日 上午10:02:26
	 * @author:liugui
	 */
	public int getHomePageAdvertisementCount(Map<String,Object> paramsMap);
	/**
	 * @description 新增广告
	 * @method  saveAdvertisement
	 * @param @param record
	 * @return void
	 * @date: 2018年4月23日 上午10:30:37
	 * @author:liugui
	 */
	public void  saveAdvertisement(HomePageAdvertisement record);
	/**
	 * @description 编辑广告
	 * @method  updateAdvertisementById
	 * @param @param record
	 * @return void
	 * @date: 2018年4月23日 上午10:30:53
	 * @author:liugui
	 */
	public void  updateAdvertisementById(HomePageAdvertisement record);
	/**
	 * @description 删除广告
	 * @method  deleteAdvertisementRecords
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月23日 上午10:30:21
	 * @author:liugui
	 */
	public void deleteAdvertisementRecords(Map<String, Object> paramsMap);
	
	/**
	 * @description 更新广告状态
	 * @method  updateStatus
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年5月7日 上午11:18:04
	 * @author:liugui
	 */
	public void updateStatus(Map<String, Object> paramsMap);
}
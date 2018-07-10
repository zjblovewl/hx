package cn.com.hxfz.service;

import java.util.List;
import java.util.Map;

import cn.com.hxfz.model.HomePageAdvertisement;

/**  
 * @类功能说明：藏城首页广告表业务处理
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年4月23日 上午上午9:41:06 
 * @版本：V1.0  
 */
public interface HomePageAdvertisementService {
	/**
	 * @description 获取藏城首页广告
	 * @method  getAdvertisementRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<HomePageAdvertisement>
	 * @date: 2018年4月23日 上午9:58:26
	 * @author:liugui
	 */
	public List<HomePageAdvertisement> getAdvertisementRecords(Map<String,Object> paramsMap);
	/**
	 * @description 广告总数
	 * @method  getAdvertisementCount
	 * @param @return
	 * @return int
	 * @date: 2018年4月23日 上午9:58:53
	 * @author:liugui
	 */
	public int getAdvertisementCount(Map<String,Object> paramsMap);
	/**
	 * @description  保存广告信息
	 * @method  saveOrUpdateAdver
	 * @param @param homePageAdvertisement
	 * @return void
	 * @date: 2018年4月23日 上午10:25:50
	 * @author:liugui
	 */
	public void saveOrUpdateAdver(HomePageAdvertisement homePageAdvertisement);
	
	/**
	 * @description 删除广告
	 * @method  deleteAdvertisementByIds
	 * @param @param params
	 * @return void
	 * @date: 2018年4月23日 上午10:37:09
	 * @author:liugui
	 */
    public void deleteAdvertisementByIds(Map<String,Object> params);
    
    /**
	 * 
	 * @description 删除图片
	 * @method  deleteImageByIds
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月18日 下午5:08:20
	 * @author:liugui
	 */
	public void deleteImageByIds(Map<String, String> paramsMap);
	
	/**
	 * @description 更新状态
	 * @method  updateStatus
	 * @param @param params
	 * @return void
	 * @date: 2018年5月7日 上午11:22:49
	 * @author:liugui
	 */
	public void updateStatus(Map<String,Object> params);
}

package cn.com.hxfz.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hxfz.dao.GoodsComplaintDao;
import cn.com.hxfz.service.GoodsComplaintService;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月3日 下午2:55:53 
 * @版本：V1.0  
 */
@Service("goodsComplaintService")
public class GoodsComplaintServiceImpl implements GoodsComplaintService{
	static Logger logger = Logger.getLogger(GoodsComplaintServiceImpl.class
			.getName());
	
	@Autowired
	private GoodsComplaintDao goodsComplaintDao;

	@Override
	public List<Map<String, Object>> getGoodsComplaintList(
			Map<String, Object> param) {
		return goodsComplaintDao.getGoodsComplaintList(param);
	}

	@Override
	public int getGoodsComplaintListCount(Map<String, Object> param) {
		return goodsComplaintDao.getGoodsComplaintCount(param);
	}

	@Override
	public void deleteGC(Map<String, Object> paramsMap) {
		goodsComplaintDao.deleteGCById(paramsMap);
	}

	@Override
	public void delMoreGC(Map<String, Object> paramsMap) {
		goodsComplaintDao.delMoreGC(paramsMap);
	}

	@Override
	public void saveGcInfo(Map<String, Object> paramsMap) {
		goodsComplaintDao.updateGoodsComInfoById(paramsMap);
	}
}

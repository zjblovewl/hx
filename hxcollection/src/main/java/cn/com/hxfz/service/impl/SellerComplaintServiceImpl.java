package cn.com.hxfz.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hxfz.dao.SellerComplaintDao;
import cn.com.hxfz.service.SellerComplaintService;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月3日 下午2:58:06 
 * @版本：V1.0  
 */
@Service("sellerComplaintService")
public class SellerComplaintServiceImpl implements SellerComplaintService{
	static Logger logger = Logger.getLogger(SellerComplaintServiceImpl.class
			.getName());
	
	@Autowired
	private SellerComplaintDao sellerComplaintDao;
	
	@Override
	public List<Map<String, Object>> getSellerComplaintList(
			Map<String, Object> param) {
		return sellerComplaintDao.getSellerComplaintList(param);
	}

	@Override
	public int getSellerComplaintCount(Map<String, Object> param) {
		return sellerComplaintDao.getSellerComplaintCount(param);
	}

	@Override
	public void deleteSC(Map<String, Object> paramsMap) {
		sellerComplaintDao.deleteSCById(paramsMap);
	}

	@Override
	public void delMoreSC(Map<String, Object> paramsMap) {
		sellerComplaintDao.delMoreSC(paramsMap);
	}

	@Override
	public void saveScInfo(Map<String, Object> paramsMap) {
		sellerComplaintDao.updateSellerComInfoById(paramsMap);
	}
	
}

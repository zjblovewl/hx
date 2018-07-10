package cn.com.hxfz.dao;

import java.util.Map;

import cn.com.hxfz.model.SysImageRel;

/**
 * 
 * @类功能说明：系统公共图片和关系表 
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年4月17日 下午7:31:42 
 * @版本：V1.0
 */
public interface ImageRelateDao {
	/**
	 * @description 删除图片表数据
	 * @method  deleteImageByIds
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月17日 下午7:39:38
	 * @author:liugui
	 */
	public void deleteImageByIds(Map<String, Object> paramsMap);
	/**
	 * @description 保存系统公共图片和分类关系表 
	 * @method  deleteClassifyByIds
	 * @param @param bean
	 * @return void
	 * @date: 2018年4月17日 下午7:35:07
	 * @author:liugui
	 */
	public void saveImageClassRel(SysImageRel bean);
	/**
	 * @description 删除系统公共图片和分类关系表
	 * @method  deleteImageClassRel
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月17日 下午7:35:52
	 * @author:liugui
	 */
	public void deleteImageClassRel(Map<String, Object> paramsMap);
	/**
	 * @description 保存系统公共图片和藏品关系表
	 * @method  saveImageGoodsRel
	 * @param @param bean
	 * @return void
	 * @date: 2018年4月17日 下午7:40:25
	 * @author:liugui
	 */
	public void saveImageGoodsRel(SysImageRel bean);
	/**
	 * @description  删除系统公共图片和藏品关系表
	 * @method  deleteImageGoodsRel
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月23日 下午1:53:17
	 * @author:liugui
	 */
	public void deleteImageGoodsRel(Map<String, Object> paramsMap);
	/**
	 * @description 保存系统公共图片和用户关系表 
	 * @method  saveImageUserRel
	 * @param @param bean
	 * @return void
	 * @date: 2018年4月17日 下午7:40:38
	 * @author:liugui
	 */
	public void saveImageUserRel(SysImageRel bean);
	/**
	 * @description   删除系统公共图片和用户关系表
	 * @method  deleteImageUserRel
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月23日 下午1:53:48
	 * @author:liugui
	 */
	public void deleteImageUserRel(Map<String, Object> paramsMap);
	/**
	 * @description 保存系统公共图片和实名认证关系表
	 * @method  saveImageAuthenticationRel
	 * @param @param bean
	 * @return void
	 * @date: 2018年4月17日 下午7:40:49
	 * @author:liugui
	 */
	public void saveImageAuthenticationRel(SysImageRel bean);
	/**
	 * @description 删除系统公共图片和实名认证关系表
	 * @method  saveImageAuthenticationRel
	 * @param @param bean
	 * @return void
	 * @date: 2018年4月17日 下午7:40:49
	 * @author:liugui
	 */
	public void deleteImageAuthenticationRel(Map<String, Object> paramsMap);
	/**
	 * @description 保存系统公共图片和广告关系表
	 * @method  saveImageAdvertisementRel
	 * @param @param bean
	 * @return void
	 * @date: 2018年4月17日 下午7:40:58
	 * @author:liugui
	 */
	public void saveImageAdvertisementRel(SysImageRel bean);
	/**
	 * @description 删除系统公共图片和广告关系表
	 * @method  deleteImageAdvertisementRRel
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月23日 下午1:54:18
	 * @author:liugui
	 */
	public void deleteImageAdvertisementRel(Map<String, Object> paramsMap);
	/**
	 * @description 保存系统公共图片和订单藏品关系表
	 * @method  saveImageOrdergoodsRel
	 * @param @param bean
	 * @return void
	 * @date: 2018年4月17日 下午7:41:06
	 * @author:liugui
	 */
	public void saveImageOrdergoodsRel(SysImageRel bean);
	/**
	 * @description 删除系统公共图片和订单藏品关系表
	 * @method  deleteImageOrdergoodsRel
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月23日 下午2:32:45
	 * @author:liugui
	 */
	public void deleteImageOrdergoodsRel(Map<String, Object> paramsMap);
}

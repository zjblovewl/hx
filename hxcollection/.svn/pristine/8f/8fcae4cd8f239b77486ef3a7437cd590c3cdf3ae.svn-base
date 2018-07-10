package cn.com.hxfz.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hxfz.dao.GoodsClassificationDao;
import cn.com.hxfz.dao.ImageRelateDao;
import cn.com.hxfz.model.GoodsClassification;
import cn.com.hxfz.model.SysImageRel;
import cn.com.hxfz.service.GoodsClassificationService;
import cn.com.hxfz.util.CommUtils;
import cn.com.hxfz.util.StringUtil;
import cn.com.hxfz.util.StringUtils;

/**
 * 
 * @类功能说明：藏品分类实现类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年4月10日 下午2:22:36 
 * @版本：V1.0
 */
@Service
public class GoodsClassificationServiceImpl implements
		GoodsClassificationService {
	private GoodsClassificationDao goodsClassificationDao;
	public GoodsClassificationDao getGoodsClassificationDao() {
		return goodsClassificationDao;
	}
	@Autowired
	public void setGoodsClassificationDao(GoodsClassificationDao goodsClassificationDao) {
		this.goodsClassificationDao = goodsClassificationDao;
	}
	private ImageRelateDao imageRelateDao;
	public ImageRelateDao getImageRelateDao() {
		return imageRelateDao;
	}
	@Autowired
	public void setImageRelateDao(ImageRelateDao imageRelateDao) {
		this.imageRelateDao = imageRelateDao;
	}
	/**
	 * 
	 * @description  获取藏品交易分类树
	 * @method  getTransClassifyRecords
	 * @param @param paramsMap
	 * @param @return
	 * @return List<GoodsClassification>
	 * @date: 2018年4月10日 下午2:23:38
	 * @author:liugui
	 */
	public List<GoodsClassification> getClassifyRecords(Map<String, Object> paramsMap) {
		// 最后的结果
		List<GoodsClassification> list = new ArrayList<GoodsClassification>();  
		// 原始的数据
		List<GoodsClassification> classList = goodsClassificationDao.getClassifyRecords(paramsMap);
		// 先找到所有的大类
	    for (GoodsClassification classBig : classList) {
	        // 大类的parentCode=0
	        if ("0".equals(classBig.getParentCode())) {
	        	list.add(classBig);
	        	for (GoodsClassification classSmall : classList) {	
	    	        // 遍历所有节点，将大类Code与当前code比较，相等就是大类下的小类
	    	        if (StringUtil.isNotBlank(classSmall.getParentCode())) {
	    	            if (classSmall.getParentCode().equals(classBig.getClassCode())) {
	    	            	list.add(classSmall);
	    	            }
	    	        }
	    	    }
	        }
	    }
	    return list;
	}
	/**
	 * 
	 * @description  查看藏品分类详细信息
	 * @method  getGoodsClassifyDetail
	 * @param @param paramsMap
	 * @param @return
	 * @return GoodsClassification
	 * @date: 2018年4月10日 下午2:27:01
	 * @author:liugui
	 */
	public GoodsClassification getGoodsClassifyDetail(Map<String, String> paramsMap) {
		return  goodsClassificationDao.getClassifyById(paramsMap);
	}
	/**
	 * 
	 * @description 删除藏品分类信息
	 * @method  deleteClassifyByIds
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月10日 下午2:27:24
	 * @author:liugui
	 */
	public void deleteClassifyByIds(Map<String, String> paramsMap){
		// 删除藏品分类id
		List<String> classIdList = new ArrayList<String>();
		// 删除藏品分类图片id
		List<String> imageIdList = new ArrayList<String>();
		String id = paramsMap.get("classId");
		if(StringUtils.isNotEmpty(id)){
			classIdList.add(id);
			// 查询分类详细信息  
			GoodsClassification result = goodsClassificationDao.getClassifyById(paramsMap);
			// 添加classId下面的子类到classIdList
			getChildIdList(classIdList,imageIdList,result.getClassCode(),paramsMap);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("classIdList", classIdList);
			// 删除藏品分类
			goodsClassificationDao.deleteClassifyByIds(params);
		}
		// 删除藏品分类图片信息
		if(StringUtil.availableStr(paramsMap.get("imageIds"))){
			String[] imageIds = paramsMap.get("imageIds").split(",");
			for(int i = 0;i < imageIds.length;i++){
				if(StringUtil.availableStr(imageIds[i])){
					imageIdList.add(imageIds[i]);
				}
			}
			Map<String, Object> paramsImg = new HashMap<String, Object>();
			paramsImg.put("imageIdList", imageIdList);
			paramsImg.put("serviceIdList", classIdList);
			imageRelateDao.deleteImageByIds(paramsImg);
			imageRelateDao.deleteImageClassRel(paramsImg);
		}
	}
	// 获取下级子分类列表
	private List<String>  getChildIdList(List<String> classIdList,List<String> imageIdList,String parentCode,Map<String, String> params){
		Map<String, Object> paramsMap = new HashMap<String, Object>();											
		paramsMap.put("parentCode", parentCode);
		paramsMap.put("classType", params.get("classType"));
		List<GoodsClassification> classList = goodsClassificationDao.getClassifyRecords(paramsMap);
		if(classIdList.size()>0){
			for(GoodsClassification clas : classList){
				classIdList.add(clas.getClassId());
				if(StringUtil.availableStr(clas.getImageIds())){
					String[] imageIds = clas.getImageIds().split(",");
					for(int i = 0;i < imageIds.length;i++){
						if(StringUtil.availableStr(imageIds[i])){
							imageIdList.add(imageIds[i]);
						}
					}
				}
			}
		}
		return classIdList;
	}
	
	/**
	 * 
	 * @description 校验分类是否已存在 同一级别下的分类名称不能相同
	 * @method  checkSameClassName
	 * @param @param paramsMap
	 * @param @return
	 * @return Boolean
	 * @date: 2018年4月10日 下午3:43:44
	 * @author:liugui
	 */
	public Boolean checkSameClassName(Map<String, Object> paramsMap){
		int count = 0;
		//result true:校验通过,false:校验不通过
		Boolean result = false;
		//不为空,则为编辑
		String classId = paramsMap.get("class_id").toString();
		if(StringUtil.availableStr(classId)){
			count = goodsClassificationDao.checkEditClassify(paramsMap);
			if(count > 0){
				result = false;
			}else{
				result = true;
			}			
		}else{
			count = goodsClassificationDao.checkAddClassify(paramsMap);
			if(count > 0){
				result = false;
			}else{
				result = true;
			}	
		}
		return result;
	}
	
	/**
	 * 
	 * @description 保存交易分类信息
	 * @method  saveOrUpdateTransClass
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月10日 下午4:05:24
	 * @author:liugui
	 */
	public void saveOrUpdateClass(Map<String, Object> paramsMap){
		String classId = paramsMap.get("classId").toString();
		//classId不为空,编辑
		if(StringUtil.availableStr(classId)){
			goodsClassificationDao.updateClassifyById(paramsMap);
		}else{
			classId = CommUtils.getUUID();
			paramsMap.put("classId", classId);
			goodsClassificationDao.saveClassify(paramsMap);
		}
		// 先删除藏品分类图片信息，再新增图片
		// 删除藏品分类id
		List<String> classIdList = new ArrayList<String>();
		classIdList.add(classId);
		Map<String, Object> paramsImg = new HashMap<String, Object>();
		paramsImg.put("serviceIdList", classIdList);
		imageRelateDao.deleteImageClassRel(paramsImg);
		//imageIds不为空
		if(StringUtil.availableStr(paramsMap.get("imageIds").toString())){
			String[] imageIds = paramsMap.get("imageIds").toString().split(",");
			int sort = 1;
			for(int i = 0;i < imageIds.length;i++){
				if(StringUtil.availableStr(imageIds[i])){
					SysImageRel bean = new SysImageRel();
					bean.setId(CommUtils.getUUID());
					bean.setImage_id(imageIds[i]);
					bean.setService_id(classId);
					bean.setSort(sort++);
					//新增到系统公共图片和分类关系表
					imageRelateDao.saveImageClassRel(bean);
				}
			}
		}
	}
	
	/**
	 * @description 校验分类是否已存在  同一级别下的分类CODE不能相同
	 * @method  checkSameClassCode
	 * @param @param paramsMap
	 * @param @return
	 * @return Boolean
	 * @date: 2018年4月12日 下午4:19:30
	 * @author:liugui
	 */
	public Boolean checkSameClassCode(Map<String, Object> paramsMap){
		int count = 0;
		//result true:校验通过,false:校验不通过
		Boolean result = false;
		String classId = paramsMap.get("class_id").toString();
		//不为空,则为编辑
		if(StringUtil.availableStr(classId)){
			count = goodsClassificationDao.checkEditClassCode(paramsMap);
			if(count > 0){
				result = false;
			}else{
				result = true;
			}			
		}else{
			count = goodsClassificationDao.checkAddClassCode(paramsMap);
			if(count > 0){
				result = false;
			}else{
				result = true;
			}	
		}
		return result;
	}
	
	/**
	 * @description 获取藏品大小类下拉框值
	 * @method  getGoodsClass
	 * @param @param paramsMap
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年4月17日 上午10:22:19
	 * @author:liugui
	 */
	public List<Map<String, Object>> getGoodsClass(Map<String, Object> paramsMap){
		List<Map<String, Object>> listNew = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listOld = goodsClassificationDao.getGoodsClass(paramsMap);
		String isAll = paramsMap.get("isAll").toString();
		if("true".equals(isAll)){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("class_code", "all");
			map.put("class_name", "全部");
			listNew.add(map);
			 for (int i = 0; i < listOld.size(); i++) {
				 listNew.add(listOld.get(i));
			 }
		}else{
			listNew = listOld;
		}
		return listNew;
	}
	
	/**
	 * 
	 * @description 删除图片
	 * @method  deleteImageByIds
	 * @param @param paramsMap
	 * @return void
	 * @date: 2018年4月18日 下午5:08:20
	 * @author:liugui
	 */
	public void deleteImageByIds(Map<String, String> paramsMap){
		// 删除藏品分类id
		List<String> imageIdList = new ArrayList<String>();
		// 删除藏品分类图片信息
		if(StringUtil.availableStr(paramsMap.get("imageIds"))){
			String[] imageIds = paramsMap.get("imageIds").split(",");
			imageIdList = Arrays.asList(imageIds);
			Map<String, Object> paramsImg = new HashMap<String, Object>();
			paramsImg.put("imageIdList", imageIdList);
			imageRelateDao.deleteImageByIds(paramsImg);
		}
	}
}

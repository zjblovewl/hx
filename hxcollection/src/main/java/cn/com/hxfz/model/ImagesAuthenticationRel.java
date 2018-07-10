package cn.com.hxfz.model;
/**  
 * @类功能说明：系统公共图片和实名认证关系实体类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月8日 下午2:13:57 
 * @版本：V1.0  
 */
public class ImagesAuthenticationRel {
	
	private String id;
	private String image_id;
	private String service_id;
	private Integer sort;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImage_id() {
		return image_id;
	}
	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	

}

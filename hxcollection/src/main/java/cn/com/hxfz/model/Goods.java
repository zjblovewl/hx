package cn.com.hxfz.model;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * @类功能说明：藏品bean
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：liugui  
 * @创建时间：2018年4月13日 上午9:25:11 
 * @版本：V1.0
 */
public class Goods {
	private String goodsId;
	private String bigClassCode;
	private String smallClassVode;
	private String userId;
	private String nickName;
	private String goodsType;
	private String goodsName;
	private String goodsDescription;
	private Integer inventory;
	private BigDecimal price;
	private BigDecimal postage;
	private String status;
	private Date firstStepTime;
	private Date lastUpdateTime;
	private Date createTime;
	private Integer browseNum;
	private String publishCustomer;
	private Date publishTime;
	private BigDecimal startPrice;
	private BigDecimal markupPrice;
	private BigDecimal currentPrice;
	private Date endTime;
	private String cityCode;
	private String isRecommend;
	private Integer sort;
	private String imageUrls;
	private String imageIds;
	private String cityName;
	private String cityParentCode;
	
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getBigClassCode() {
		return bigClassCode;
	}
	public void setBigClassCode(String bigClassCode) {
		this.bigClassCode = bigClassCode;
	}
	public String getSmallClassVode() {
		return smallClassVode;
	}
	public void setSmallClassVode(String smallClassVode) {
		this.smallClassVode = smallClassVode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsDescription() {
		return goodsDescription;
	}
	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}
	public Integer getInventory() {
		return inventory;
	}
	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getPostage() {
		return postage;
	}
	public void setPostage(BigDecimal postage) {
		this.postage = postage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getBrowseNum() {
		return browseNum;
	}
	public void setBrowseNum(Integer browseNum) {
		this.browseNum = browseNum;
	}
	public String getPublishCustomer() {
		return publishCustomer;
	}
	public void setPublishCustomer(String publishCustomer) {
		this.publishCustomer = publishCustomer;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public BigDecimal getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}
	public BigDecimal getMarkupPrice() {
		return markupPrice;
	}
	public void setMarkupPrice(BigDecimal markupPrice) {
		this.markupPrice = markupPrice;
	}
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getImageIds() {
		return imageIds;
	}
	public void setImageIds(String imageIds) {
		this.imageIds = imageIds;
	}
	public Date getFirstStepTime() {
		return firstStepTime;
	}
	public void setFirstStepTime(Date firstStepTime) {
		this.firstStepTime = firstStepTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityParentCode() {
		return cityParentCode;
	}
	public void setCityParentCode(String cityParentCode) {
		this.cityParentCode = cityParentCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}

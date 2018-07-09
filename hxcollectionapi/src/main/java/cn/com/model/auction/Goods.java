package cn.com.model.auction;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {
    private String id;

    private String bigClassCode;

    private String smallClassCode;

    private String userId;

    private String goodsType;

    private String goodsName;

    private String goodsDescription;

    private Long inventory;

    private BigDecimal price;

    private BigDecimal postage;

    private String status;

    private Date firstStepTime;

    private Date lastUpdateTime;

    private Long browseNum;

    private String publishCustomer;

    private Date publishTime;

    private BigDecimal startPrice;

    private BigDecimal markupPrice;

    private BigDecimal currentPrice;

    private Date endTime;

    private String cityCode;

    private Date createTime;

    private String pcImageIds;

    private String mobileImageIds;

    private String isRecommend;

    private Integer sort;

    private String delFlag;

    private String endTimeStr;

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBigClassCode() {
        return bigClassCode;
    }

    public void setBigClassCode(String bigClassCode) {
        this.bigClassCode = bigClassCode == null ? null : bigClassCode.trim();
    }

    public String getSmallClassCode() {
        return smallClassCode;
    }

    public void setSmallClassCode(String smallClassCode) {
        this.smallClassCode = smallClassCode == null ? null : smallClassCode.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType == null ? null : goodsType.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription == null ? null : goodsDescription.trim();
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
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
        this.status = status == null ? null : status.trim();
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

    public Long getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Long browseNum) {
        this.browseNum = browseNum;
    }

    public String getPublishCustomer() {
        return publishCustomer;
    }

    public void setPublishCustomer(String publishCustomer) {
        this.publishCustomer = publishCustomer == null ? null : publishCustomer.trim();
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
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPcImageIds() {
        return pcImageIds;
    }

    public void setPcImageIds(String pcImageIds) {
        this.pcImageIds = pcImageIds == null ? null : pcImageIds.trim();
    }

    public String getMobileImageIds() {
        return mobileImageIds;
    }

    public void setMobileImageIds(String mobileImageIds) {
        this.mobileImageIds = mobileImageIds == null ? null : mobileImageIds.trim();
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend == null ? null : isRecommend.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }
}
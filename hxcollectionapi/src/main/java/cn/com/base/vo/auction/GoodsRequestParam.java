package cn.com.base.vo.auction;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/4/8 上午9:54
 * @版本：V1.0
 */
public class GoodsRequestParam {
    private String goods_name;//商品标题

    private String goods_description;//商品描述

    private String city_code;//城市编码

    private String image_ids;//图片ID，以逗号分割

    private BigDecimal start_price;//起拍价

    private BigDecimal markup_price;//加价

    private String big_class_code;//大类

    private String small_class_code;//小类

    private Long inventory;//库存

    private BigDecimal postage;//邮费

    private Long end_time;//结拍时间

    public String getImage_ids() {
        return image_ids;
    }

    public void setImage_ids(String image_ids) {
        this.image_ids = image_ids;
    }

    public Long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Long end_time) {
        this.end_time = end_time;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_description() {
        return goods_description;
    }

    public void setGoods_description(String goods_description) {
        this.goods_description = goods_description;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public BigDecimal getStart_price() {
        return start_price;
    }

    public void setStart_price(BigDecimal start_price) {
        this.start_price = start_price;
    }

    public BigDecimal getMarkup_price() {
        return markup_price;
    }

    public void setMarkup_price(BigDecimal markup_price) {
        this.markup_price = markup_price;
    }

    public String getBig_class_code() {
        return big_class_code;
    }

    public void setBig_class_code(String big_class_code) {
        this.big_class_code = big_class_code;
    }

    public String getSmall_class_code() {
        return small_class_code;
    }

    public void setSmall_class_code(String small_class_code) {
        this.small_class_code = small_class_code;
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

}

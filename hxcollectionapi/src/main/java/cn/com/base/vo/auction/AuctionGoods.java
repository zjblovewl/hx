package cn.com.base.vo.auction;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @类功能说明：拍卖列表返回实体bean
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/4/3 上午17:22
 * @版本：V1.0
 */
public class AuctionGoods {
    private String id;//商品ID

    private Date end_time;//结拍时间

    private Date current_time;//当前时间

    private String goods_name;//商品标题

    private String user_id;//创建人ID

    private String nick_name;//店铺名称

    private String head_url;//头像url

    private String head1_url;//头像url

    private String head2_url;//头像url

    private Integer offer_count;//竞拍次数

    private BigDecimal offer_price;//竞拍价格

    private BigDecimal start_price;//起拍价格

    private String image_urls;//原图

    private String small_urls;//200*200规格

    private String big_urls;//800*800规格

    public BigDecimal getStart_price() {
        return start_price;
    }

    public void setStart_price(BigDecimal start_price) {
        this.start_price = start_price;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(Date current_time) {
        this.current_time = current_time;
    }

    public String getHead1_url() {
        return head1_url;
    }

    public void setHead1_url(String head1_url) {
        this.head1_url = head1_url;
    }

    public String getHead2_url() {
        return head2_url;
    }

    public void setHead2_url(String head2_url) {
        this.head2_url = head2_url;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getImage_urls() {
        return image_urls;
    }

    public void setImage_urls(String image_urls) {
        this.image_urls = image_urls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }

    public Integer getOffer_count() {
        return offer_count;
    }

    public void setOffer_count(Integer offer_count) {
        this.offer_count = offer_count;
    }

    public BigDecimal getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(BigDecimal offer_price) {
        this.offer_price = offer_price;
    }

    public String getSmall_urls() {
        return small_urls;
    }

    public void setSmall_urls(String small_urls) {
        this.small_urls = small_urls;
    }

    public String getBig_urls() {
        return big_urls;
    }

    public void setBig_urls(String big_urls) {
        this.big_urls = big_urls;
    }
}

package cn.com.base.vo.auction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @类功能说明：出价记录列表
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/4/8 上午10:46
 * @版本：V1.0
 */
public class GoodsOfferRecordDetail {
    private String user_id;//用户ID

    private String nick_name;//用户昵称

    private Date current_time;//当前时间

    private String head_url;//头像url

    private String head1_url;//头像url

    private String head2_url;//头像url

    private BigDecimal offer_price;//出价

    private Date create_time;//创建时间

    private String goods_id;

    public Date getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(Date current_time) {
        this.current_time = current_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
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

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
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

    public BigDecimal getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(BigDecimal offer_price) {
        this.offer_price = offer_price;
    }
}

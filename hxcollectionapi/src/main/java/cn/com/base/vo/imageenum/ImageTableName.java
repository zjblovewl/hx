package cn.com.base.vo.imageenum;

/**
 * @类功能说明：图片表枚举类
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/4/17 上午15:59
 * @版本：V1.0
 */
public enum ImageTableName {
    GOODS_TABLE_NAME("hx_images_goods_rel"),//商品表
    ADVERTISEMENT_TABLE_NAME("hx_images_advertisement_rel"),//广告表
    AUTHENTICATION_TABLE_NAME("hx_images_authentication_rel"),//实名认证表
    CLASS_TABLE_NAME("hx_images_class_rel"),//大小类表
    ORDERGOODS_TABLE_NAME("hx_images_ordergoods_rel"),//订单商品表
    USER_TABLE_NAME("hx_images_user_rel");//用户表

    private String tableName;

    ImageTableName(String tableName){

        this.tableName = tableName;

    }

    public String getTableName() {

        return tableName;

    }



}

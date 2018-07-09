package cn.com.base.vo.auction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @类功能说明：小类对象
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/30 上午9:16
 * @版本：V1.0
 */
public class ChildClassIfication implements Serializable{

    /**
     * 小类Id
     */
    private String class_id;

    /**
     * 小类编码
     */
    private String class_code;

    /**
     * 原图
     */
    private String imageUrl;

    /**
     * 手机端200*200
     */
    private String image1Url;

    /**
     * 手机端800*800
     */
    private String image2Url;

    /**
     * PC端200*200
     */
    @JsonIgnore
    private String image3Url;

    /**
     * PC端200*200
     */
    @JsonIgnore
    private String image4Url;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImage1Url() {
        return image1Url;
    }

    public void setImage1Url(String image1Url) {
        this.image1Url = image1Url;
    }

    public String getImage2Url() {
        return image2Url;
    }

    public void setImage2Url(String image2Url) {
        this.image2Url = image2Url;
    }

    public String getImage3Url() {
        return image3Url;
    }

    public void setImage3Url(String image3Url) {
        this.image3Url = image3Url;
    }

    public String getImage4Url() {
        return image4Url;
    }

    public void setImage4Url(String image4Url) {
        this.image4Url = image4Url;
    }

    /**
     * 小类图片名称
     */
    private String class_name;

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
}

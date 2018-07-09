package cn.com.base.vo.auction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * @类功能说明：大小类返回数据对象
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/30 上午9:15
 * @版本：V1.0
 */
public class ParentClassIfication implements Serializable{

    /**
     * 大类ID
     */
    private String parent_class_id;

    /**
     * 大类编码
     */
    private String parent_class_code;

    /**
     * 大类名称
     */
    private String parent_class_name;

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
     * 小类集合
     */
    private List<ChildClassIfication> small_class_list;

    public String getParent_class_id() {
        return parent_class_id;
    }

    public void setParent_class_id(String parent_class_id) {
        this.parent_class_id = parent_class_id;
    }

    public String getParent_class_code() {
        return parent_class_code;
    }

    public void setParent_class_code(String parent_class_code) {
        this.parent_class_code = parent_class_code;
    }

    public String getParent_class_name() {
        return parent_class_name;
    }

    public void setParent_class_name(String parent_class_name) {
        this.parent_class_name = parent_class_name;
    }

    public List<ChildClassIfication> getSmall_class_list() {
        return small_class_list;
    }

    public void setSmall_class_list(List<ChildClassIfication> small_class_list) {
        this.small_class_list = small_class_list;
    }
}

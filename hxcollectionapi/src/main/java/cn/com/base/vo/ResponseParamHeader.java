package cn.com.base.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @类功能说明：响应头部分对象
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/21 上午10:13
 * @版本：V1.0
 */
public class ResponseParamHeader implements Serializable {

    private Integer resp_code;//响应状态码

    private String resp_msg;//响应提示语

    private String user_id;//用户ID

    private String token;//服务端生成Token

    public Integer getResp_code() {
        return resp_code;
    }

    public void setResp_code(Integer resp_code) {
        this.resp_code = resp_code;
    }

    public String getResp_msg() {
        return resp_msg;
    }

    public void setResp_msg(String resp_msg) {
        this.resp_msg = resp_msg;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

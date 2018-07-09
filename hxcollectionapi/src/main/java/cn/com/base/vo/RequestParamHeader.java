package cn.com.base.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @类功能说明：请求参数头对象
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/21 上午10:07
 * @版本：V1.0
 */
public class RequestParamHeader {
    private static final long serialVersionUID = -5125820210117783676L;

    private long app_id;//应用ID

    private String token;//Token 由服务端生成

    private String signature;//签名

    private long timestamp;//时间戳

    private String user_id;//用户ID

    private String is_source;//来源(1、手机端 2、PC端)

    private String device_info;//设备信息

    private String version;//版本

    public RequestParamHeader() {
    }

    public RequestParamHeader(long app_id, String token, String signature, long timestamp, String user_id, String is_source, String device_info, String version) {
        this.app_id = app_id;
        this.token = token;
        this.signature = signature;
        this.timestamp = timestamp;
        this.user_id = user_id;
        this.is_source = is_source;
        this.device_info = device_info;
        this.version = version;
    }

    public long getApp_id() {
        return app_id;
    }

    public void setApp_id(long app_id) {
        this.app_id = app_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getIs_source() {
        return is_source;
    }

    public void setIs_source(String is_source) {
        this.is_source = is_source;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

package cn.com.base.vo;

import java.io.Serializable;

/**
 * @类功能说明：请求入参参数
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/21 上午10:05
 * @版本：V1.0
 */
public class RequestParamVo implements Serializable {
    private static final long serialVersionUID = -5125820210117783676L;

    private RequestParamHeader header;//请求头

    private Object body;//请求随机参数

    public RequestParamHeader getHeader() {
        return header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public void setHeader(RequestParamHeader header) {
        this.header = header;
    }

    public RequestParamVo() {
    }

    public RequestParamVo(RequestParamHeader header, Object body) {
        this.header = header;
        this.body = body;
    }
}

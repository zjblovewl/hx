package cn.com.base.vo;

/**
 * @类功能说明：响应参数对象
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/21 上午10:13
 * @版本：V1.0
 */
public class ResponseParamVo {
    private ResponseParamHeader header;

    private Object body;

    public ResponseParamHeader getHeader() {
        return header;
    }

    public void setHeader(ResponseParamHeader header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public ResponseParamVo() {
    }

    public ResponseParamVo(ResponseParamHeader header, Object body) {
        this.header = header;
        this.body = body;
    }

}

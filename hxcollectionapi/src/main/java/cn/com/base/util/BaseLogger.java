package cn.com.base.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @类功能说明：初始化日志对象
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/5/9 上午10:19
 * @版本：V1.0
 */
public class BaseLogger {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    public final ObjectMapper objectMapper = new ObjectMapper();
}

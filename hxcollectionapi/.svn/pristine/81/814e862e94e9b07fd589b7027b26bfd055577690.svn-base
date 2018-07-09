package cn.com.base.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @类功能说明：业务常量类
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/13 上午11:13
 * @版本：V1.0
 */
@Component
@ConfigurationProperties(prefix = "bussinessConstant")
@PropertySource(value = "classpath:bussinesconstant.yml")//配置文件路径
public class BussinesConstant {

    /**
     * 不校验Token请求的url后缀
     */
    @Value("${urls}")
    private String urls;

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}

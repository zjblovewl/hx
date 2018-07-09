package cn.com.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import cn.com.base.util.BaseLogger;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年5月31日 上午11:33:35 
 * @版本：V1.0  
 */
@Component  
public class Consumer extends BaseLogger{  
    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息  
    @JmsListener(destination = "mytest.queue")  
    public void receiveQueue(String text) {  
    	log.info("Consumer收到的报文为:"+text);  
    }  
}  

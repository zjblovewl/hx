package cn.com.activemq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.com.activemq.Producer;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年6月1日 上午10:58:12 
 * @版本：V1.0  
 */
@Order(2)//解决启动时实现类执行优先级，越小越往前执行
@Component
public class InitActiveMQ implements CommandLineRunner{
	
	private Logger logger = LoggerFactory.getLogger(InitActiveMQ.class);
	
    @Autowired  
    private Producer producer;
    
	@Override
	public void run(String... args) throws Exception {
		logger.info("初始化activeMQ任务开始...");
		producer.timingSend("my name is chenchen10000L", "mytest.queue", System.currentTimeMillis()+10000L);
        producer.timingSend("my name is chenchen15000L", "mytest.queue", System.currentTimeMillis()+15000L);
        producer.timingSend("my name is chenchen20000L", "mytest.queue", System.currentTimeMillis()+20000L);
        
        producer.timingSend("my name is chenchen10000L", "mytest.queue1", System.currentTimeMillis()+10000L);
        producer.timingSend("my name is chenchen15000L", "mytest.queue1", System.currentTimeMillis()+15000L);
        producer.timingSend("my name is chenchen20000L", "mytest.queue1", System.currentTimeMillis()+20000L);
        logger.info("初始化activeMQ任务结束...");
	}

}

package cn.com.activemq;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年5月31日 下午2:13:51 
 * @版本：V1.0  
 */
@RunWith(SpringRunner.class)  
@SpringBootTest  
public class SpringbootJmsApplicationTests {  
      
    @Autowired  
    private Producer producer;  
      
    @Test  
    public void contextLoads() throws InterruptedException {  
    	/*Destination destination = new ActiveMQQueue("mytest.queue");  
          
        for(int i=0; i<100; i++){  
            producer.sendMessage(destination, "myname is chhliu!!!");  
        }*/
        
       /* producer.timingSend("my name is chenchen10000L", "mytest.queue", System.currentTimeMillis()+10000L);
        producer.timingSend("my name is chenchen15000L", "mytest.queue", System.currentTimeMillis()+15000L);
        producer.timingSend("my name is chenchen20000L", "mytest.queue", System.currentTimeMillis()+20000L);
        
        producer.timingSend("my name is chenchen10000L", "mytest.queue1", System.currentTimeMillis()+10000L);
        producer.timingSend("my name is chenchen15000L", "mytest.queue1", System.currentTimeMillis()+15000L);
        producer.timingSend("my name is chenchen20000L", "mytest.queue1", System.currentTimeMillis()+20000L);*/
    }  
    


} 

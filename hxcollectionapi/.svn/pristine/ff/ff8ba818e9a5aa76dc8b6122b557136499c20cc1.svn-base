package cn.com.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年5月31日 上午11:22:40 
 * @版本：V1.0  
 */
@Service("producer") 
public class Producer {
	@Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装  
    private JmsMessagingTemplate jmsTemplate;  
    // 发送消息，destination是发送到的队列，message是待发送的消息  
    public void sendMessage(Destination destination, final String message){  
        jmsTemplate.convertAndSend(destination, message);  
    }  
    
    /**
     * @description activeMQ定时发消息
     * @method  timingSend
     * @param @param message
     * @param @param queueName
     * @param @param timeStamp
     * @return void
     * @date: 2018年5月31日 下午8:34:38
     * @author:chenchen
     */
    public void timingSend(String message, String queueName, Long timeStamp) {
	   //获取连接工厂
	   ConnectionFactory connectionFactory = jmsTemplate.getConnectionFactory();
	   try {
	      //获取连接
	      Connection connection = connectionFactory.createConnection();
	      connection.start();
	      //获取session
	      Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
	      // 创建一个消息队列
	      Destination destination = session.createQueue(queueName);
	      MessageProducer producer = session.createProducer(destination);
	      producer.setDeliveryMode(DeliveryMode.PERSISTENT);
	      TextMessage textMessage = session.createTextMessage(message);
	      //设置延迟时间
	      textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, timeStamp-System.currentTimeMillis());
	      //发送
	      producer.send(textMessage);
	      session.commit();
	      producer.close();
	      session.close();
	      connection.close();
	   } catch (Exception e) {
	      e.getMessage();
	   }
    }
}

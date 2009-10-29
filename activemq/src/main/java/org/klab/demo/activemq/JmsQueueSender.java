package org.klab.demo.activemq;

import java.util.HashMap;
import java.util.Map;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;

public class JmsQueueSender {

    private JmsTemplate jmsTemplate;
    private Queue queue;

    public void setConnectionFactory(ConnectionFactory cf) {
        this.jmsTemplate = new JmsTemplate(cf);
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void simpleSend() {
        this.jmsTemplate.send(this.queue, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
              return session.createTextMessage("hello queue world");
            }
        });
    }
    
    public void sendWithConversion() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Name", "Mark");
        map.put("Age", new Integer(47));
        jmsTemplate.convertAndSend("testQueue", map, new MessagePostProcessor() {
            public Message postProcessMessage(Message message) throws JMSException {
                message.setIntProperty("AccountID", 1234);
                message.setJMSCorrelationID("123-00001");
                return message;
            }
        });
    }
}
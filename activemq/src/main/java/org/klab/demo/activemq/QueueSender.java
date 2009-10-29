package org.klab.demo.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueSender {

	@Autowired
    private JmsTemplate jmsTemplate;
	
    public void send(String msg) {
    	for (int i = 0; i < 100; i++) {
    		jmsTemplate.convertAndSend("Queue.Name", msg);
		}
    }
}
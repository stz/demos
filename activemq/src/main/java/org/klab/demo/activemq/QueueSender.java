package org.klab.demo.activemq;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueSender {

	@Autowired
    private JmsTemplate jmsTemplate;

	@Autowired
    private Destination destination;
	
    public void send(String msg) {
    	for (int i = 0; i < 100; i++) {
    		jmsTemplate.convertAndSend(destination, msg + " " + i);
		}
    }
}
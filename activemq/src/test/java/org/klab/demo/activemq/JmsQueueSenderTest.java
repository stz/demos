package org.klab.demo.activemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "/activemq-applicationContext.xml"
})
public class JmsQueueSenderTest {

	@Autowired
	private JmsQueueSender jmsQueueSender;
	
	@Test
	public void test() {
		jmsQueueSender.simpleSend();
	}
}

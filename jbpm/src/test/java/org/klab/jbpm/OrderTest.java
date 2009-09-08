package org.klab.jbpm;

import org.jbpm.api.ProcessInstance;
import org.jbpm.test.JbpmTestCase;
import org.junit.Assert;
import org.junit.Test;

public class OrderTest extends JbpmTestCase {

	@Test
	public void testOrder() {
	    String deploymentId = repositoryService.createDeployment()
	        .addResourceFromClasspath("jpdl/order.jpdl.xml")
	        .deploy();

	    ProcessInstance processInstance = executionService.startProcessInstanceByKey("order");
	    Assert.assertNotNull(processInstance);

	    Assert.assertTrue(processInstance.isActive("Paiement"));
	    
	    System.out.println(processInstance.findActiveActivityNames());
	    String paiementId = processInstance.findActiveExecutionIn("Paiement").getId();
	    executionService.signalExecutionById(paiementId);
	    //Assert.assertTrue(processInstance.isActive("Activation"));
	    System.out.println(processInstance.findActiveActivityNames());

	    String activationId = processInstance.findActiveExecutionIn("Activation").getId();
	    executionService.signalExecutionById(paiementId);
	    
	    repositoryService.deleteDeployment(deploymentId);
	}
}

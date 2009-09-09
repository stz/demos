package org.klab.jbpm.order;

import org.jbpm.api.ProcessInstance;
import org.jbpm.test.JbpmTestCase;
import org.junit.Assert;
import org.junit.Test;

public class OrderTest extends JbpmTestCase {

	@Test
	public void testOrder() {
	    String deploymentId = repositoryService.createDeployment()
	        .addResourceFromClasspath("org/klab/jbpm/order/order.jpdl.xml")
	        .deploy();

	    ProcessInstance processInstance = executionService.startProcessInstanceByKey("order");
	    
	    Assert.assertNotNull(processInstance);
	    Assert.assertTrue(processInstance.isActive("paiement"));
	    
	    String paiementId = processInstance.findActiveExecutionIn("paiement").getId();
	    processInstance = executionService.signalExecutionById(paiementId);
	    
	    Assert.assertTrue(processInstance.isActive("activation"));
	    
	    repositoryService.deleteDeploymentCascade(deploymentId);
	}
}

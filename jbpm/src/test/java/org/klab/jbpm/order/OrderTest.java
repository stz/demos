package org.klab.jbpm.order;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.jbpm.test.JbpmTestCase;
import org.junit.Assert;
import org.junit.Test;

public class OrderTest extends JbpmTestCase {

	@Test
	public void testOrder() {
	    String deploymentId = repositoryService.createDeployment()
	        .addResourceFromClasspath("org/klab/jbpm/order/order.jpdl.xml")
	        .deploy();

	    // instanciation d'un nouveau process
	    ProcessInstance processInstance = executionService.startProcessInstanceByKey("order");
	    
	    // la première activité = paiement
	    Assert.assertNotNull(processInstance);
	    Assert.assertTrue(processInstance.isActive("paiement"));
	    
	    log(processInstance.getId());
	    
	    // execution de la tâche
	    String taskId = taskService.findPersonalTasks("client").get(0).getId();
	    //taskService.takeTask(taskId, "client");
	    taskService.completeTask(taskId);
	    
	    // envoi d'un signal au paiement
	    String paiementId = processInstance.findActiveExecutionIn("paiement").getId();
	    executionService.signalExecutionById(paiementId);

	    log(processInstance.getId());
	    
	    // l'activité courante doit etre = activation
	    processInstance = executionService.findProcessInstanceById(processInstance.getId());
	    Assert.assertTrue(processInstance.isActive("activation"));
	    
	    repositoryService.deleteDeploymentCascade(deploymentId);
	}
	
	private void log(String processId) {
		System.out.println("--------");
		ProcessInstance processInstance = executionService.findProcessInstanceById(processId);
		System.out.println("Activities :");
		for (String name : processInstance.findActiveActivityNames()) {
			System.out.println("  " + name);
		}
		System.out.println("Client tasks :");
		for (Task task : taskService.findPersonalTasks("client")) {
			System.out.println("  " + task.getName() + " (" + task.getId() + ")");
		}
	}
}

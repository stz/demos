package org.klab.jbpm.edition;

import java.util.Map;

import org.jbpm.api.activity.ActivityBehaviour;
import org.jbpm.api.activity.ActivityExecution;

public class WaitState implements ActivityBehaviour {

	private static final long serialVersionUID = 1L;

	public void execute(ActivityExecution execution) {
		System.out.println("En attente sur : \"" + execution.getActivityName() + "\"");
		execution.waitForSignal();
	}

	public void signal(ActivityExecution execution, String signalName, Map<String, Object> parameters) {
		execution.take(signalName);
	}
}
package org.klab.jbpm.edition;

import org.jbpm.api.activity.ActivityBehaviour;
import org.jbpm.api.activity.ActivityExecution;

public class Display implements ActivityBehaviour {

	private static final long serialVersionUID = 1L;
	
	String message;

	public Display(String message) {
		this.message = message;
	}

	public void execute(ActivityExecution execution) {
		System.out.println(message);
	}
}
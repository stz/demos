package org.klab.jbpm.edition;

import org.jbpm.pvm.internal.builder.CompositeBuilder;
import org.jbpm.pvm.internal.builder.ProcessDefinitionBuilder;
import org.jbpm.pvm.internal.client.ClientProcessInstance;
import org.junit.Test;

public class EditionTest {

	@Test
	public void testOrder() {
		CompositeBuilder builder = ProcessDefinitionBuilder.startProcess()
			.startActivity("edition", new WaitState()).initial()
			.transition("validation", "soumettre").endActivity();
		builder.startActivity("validation", new WaitState())
			.transition("publié", "accepter")
			.transition("edition", "rejeter")
			.endActivity();
		builder.startActivity("publié", new WaitState())
			.transition("validation", "signaler un abus")
			.endActivity();
		
		ClientProcessInstance client = builder.endProcess().startProcessInstance();
	}
}

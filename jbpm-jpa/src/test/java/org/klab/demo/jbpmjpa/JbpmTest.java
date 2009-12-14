package org.klab.demo.jbpmjpa;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "/applicationContext.xml"
})
public class JbpmTest {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ExecutionService executionService;
    
    @Test
    public void testApplicationContext() {
        
        // déploiement
        NewDeployment deployment = repositoryService.createDeployment();
        deployment.addResourceFromClasspath("workflow.jpdl.xml");
        deployment.deploy();
        
        // instanciation
        ProcessInstance process = executionService.startProcessInstanceByKey("workflow");
        
        // signal
        executionService.signalExecutionById(process.getId());
    }
}

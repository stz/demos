package org.klab.demo.jbpmjpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "/applicationContext.xml"
})
public class ApplicationContextTest {

    @Test
    public void testApplicationContext() {
    }
}

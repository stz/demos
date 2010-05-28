package org.klab.demos.ibatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "/applicationContext.xml"
})
public class IbatisTest {
    
    @Test
    public void test() {
    }
}

package org.klab.demo.hibernate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.klab.demo.hibernate.dao.impl.OrderDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "/spring.xml"
})
public class HibernateTest  {

    @Autowired
    private OrderDaoImpl orderDao;
    
    @Test
    public void testSave() {
        
    }
}
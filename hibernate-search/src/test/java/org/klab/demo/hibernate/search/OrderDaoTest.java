package org.klab.demo.hibernate.search;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.klab.demo.hibernate.search.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "/spring.xml"
})
public class OrderDaoTest  {

    @Autowired
    private OrderDao orderDao;
    
    @Test
    public void testSave() {
    }
}
package org.klab.demo.hibernate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.klab.demo.hibernate.dao.OrderDao;
import org.klab.demo.hibernate.model.Article;
import org.klab.demo.hibernate.model.Line;
import org.klab.demo.hibernate.model.Order;
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
        Long id = (Long) orderDao.save(new Order());
        Assert.assertEquals(new Long(1), id);
    }
    
    @Test
    public void testFindById() {
        Long id = createOrder();
        System.out.println("findById(" + id + "):");
        orderDao.findById(id);
    }
    
    @Test
    public void testFindByIdWithLines() {
        Long id = createOrder();
        System.out.println("findByIdWithLines(" + id + "):");
        Order order = orderDao.findByIdWithLines(id);
        System.out.println("getLines().size():");
        order.getLines().size();
    }
    
    @Test
    public void testFindByIdWithLinesAndArticles() {
        Long id = createOrder();
        System.out.println("findByIdWithLinesAndArticles(" + id + "):");
        Order order = orderDao.findByIdWithLinesAndArticles(id);
        for (Line line : order.getLines()) {
            System.out.println(line.getArticles().size());
        }
    }
    
    private Long createOrder() {
        System.out.println("createOrder():");
        Order order = new Order();
        order.getLines().add(new Line());
        order.getLines().add(new Line());
        Line line = new Line();
        line.getArticles().add(new Article());
        line.getArticles().add(new Article());
        order.getLines().add(line);
        return (Long) orderDao.save(order);
    }
}
package org.klab.demo.hibernate.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.klab.demo.hibernate.dao.OrderDao;
import org.klab.demo.hibernate.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    @Transactional
    public Serializable save(Order order) {
        return getSession().save(order);
    }

    @Override
    @Transactional
    public Order findById(Long id) {
        return (Order) getSession().get(Order.class, id);
    }

    @Override
    @Transactional
    public Order findByIdWithLines(Long id) {
        return (Order) getSession().createQuery("from Order o inner join fetch o.lines where o.id = :id")
            .setParameter("id", id)
            .uniqueResult();
    }

    @Override
    @Transactional
    public Order findByIdWithLinesAndArticles(Long id) {
        // fonctionne seulement si les collections ne sont pas des bags!!! (pas d'ArrayList donc)
        return (Order) getSession().createQuery("from Order as o inner join fetch o.lines l left join fetch l.articles where o.id = :id")
            .setParameter("id", id)
            .uniqueResult();
    }
}

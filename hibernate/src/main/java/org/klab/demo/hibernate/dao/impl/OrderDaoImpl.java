package org.klab.demo.hibernate.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.klab.demo.hibernate.model.Order;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;
    
    public Serializable save(Order order) {
        return getSession().save(order);
    }
    
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}

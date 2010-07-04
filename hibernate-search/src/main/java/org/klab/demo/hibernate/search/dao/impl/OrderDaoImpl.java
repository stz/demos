package org.klab.demo.hibernate.search.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.klab.demo.hibernate.search.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
}

package org.klab.demo.hibernate.dao;

import java.io.Serializable;

import org.klab.demo.hibernate.model.Order;

public interface OrderDao {

    Serializable save(Order order);
}

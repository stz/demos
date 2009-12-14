package org.klab.demo.jbpmjpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Client {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
package org.klab.demo.hibernate.model;

import java.awt.print.Book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

/**
 * Exemple d'utilisation de l'annotation @Any.
 */
@Entity
@Table(name = "promotions")
public class Promotion {

    @Id
    @GeneratedValue
    private Long id;
    
    @Any(metaColumn = @Column(name = "ITEM_TYPE"))
    @AnyMetaDef(idType = "long", metaType = "string", metaValues = { @MetaValue(targetEntity = Book.class, value = "B"),
            @MetaValue(targetEntity = VHS.class, value = "V"), @MetaValue(targetEntity = DVD.class, value = "D") })
    @JoinColumn(name = "ITEM_ID")
    private Object item;
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public Object getItem() {
        return item;
    }
}

package org.klab.demo.hibernate.search.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Annonce {

    @Id
    @GeneratedValue
    private Long id;
    
    @Lob
    private String texte;
    
    public Annonce() {
        super();
    }

    public Annonce(String texte) {
        super();
        this.texte = texte;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getTexte() {
        return texte;
    }
}

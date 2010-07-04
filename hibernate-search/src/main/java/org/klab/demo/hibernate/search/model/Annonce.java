package org.klab.demo.hibernate.search.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class Annonce {

    @Id
    @GeneratedValue
    @DocumentId
    private Long id;
    
    @Lob
    @Field(index = Index.TOKENIZED)
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

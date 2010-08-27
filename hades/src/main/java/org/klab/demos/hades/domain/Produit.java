package org.klab.demos.hades.domain;

import javax.persistence.Entity;

import org.synyx.hades.domain.AbstractPersistable;

@Entity
public class Produit extends AbstractPersistable<Long> {
    
    private static final long serialVersionUID = 1L;
	
    private String nom;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}

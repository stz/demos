package org.klab.demo.hibernate.search.dao;

import org.klab.demo.hibernate.search.model.Annonce;

public interface AnnonceDao {
    
    Annonce save(Annonce annonce);

    Annonce findById(Long id);
}

package org.klab.demo.hibernate.search.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.klab.demo.hibernate.search.dao.AnnonceDao;
import org.klab.demo.hibernate.search.model.Annonce;
import org.springframework.stereotype.Repository;

@Repository
public class AnnonceDaoImpl implements AnnonceDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Annonce save(Annonce annonce) {
        if (annonce.getId() == null) {
            entityManager.persist(annonce);
            return annonce;
        } else {
            return entityManager.merge(annonce);
        }
    }

    @Override
    public Annonce findById(Long id) {
        return entityManager.find(Annonce.class, id);
    }
}

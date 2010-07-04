package org.klab.demo.hibernate.search;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.klab.demo.hibernate.search.dao.AnnonceDao;
import org.klab.demo.hibernate.search.model.Annonce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class AnnonceDaoTest  {

    @Autowired
    private AnnonceDao annonceDao;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Test
    public void testBidon() throws ParseException {
        annonceDao.save(new Annonce("Vélo de course Colnago"));
        annonceDao.save(new Annonce("Velo de ville"));
        annonceDao.save(new Annonce("Vello femme, à voir"));
        annonceDao.save(new Annonce("Casserole pour faire la cuisine"));
        
        System.out.println(annonceDao.findById(1L));
        
        //Définition de l'analyseur
        QueryParser parser = new QueryParser(Version.LUCENE_29, "texte", new StandardAnalyzer(Version.LUCENE_29));
        
        //Définition du critère de recherche
        Query query = parser.parse("Velo");
            
        //création du fullTextEntityManager  
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);  

        List list = fullTextEntityManager.createFullTextQuery(query, Annonce.class).getResultList();
        System.out.println(list);
    }
}
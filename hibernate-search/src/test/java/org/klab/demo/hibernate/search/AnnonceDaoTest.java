package org.klab.demo.hibernate.search;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
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
        
        // Définition de l'analyseur
        FrenchAnalyzer analyser = new FrenchAnalyzer(Version.LUCENE_29);
        QueryParser parser = new QueryParser(Version.LUCENE_29, "texte", analyser);
        
        // Définition du critère de recherche
        Query query = parser.parse("vélo~ colnago~");
            
        // Création du fullTextEntityManager  
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);  

        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, Annonce.class);
        fullTextQuery.setProjection(FullTextQuery.SCORE, FullTextQuery.THIS);
        List<Object[]> result = fullTextQuery.getResultList();
        for (Object[] array : result) {
            Annonce annonce = (Annonce) array[1];
            System.out.println(array[0] + ", " + annonce.getTexte() + "(" + annonce.getId() + ")");
        }
    }
}
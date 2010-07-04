package org.klab.demo.hibernate.search;

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
@ContextConfiguration(locations = { "/spring.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class AnnonceDaoTest  {

    @Autowired
    private AnnonceDao annonceDao;
    
    @Test
    public void testBidon() {
        Annonce a1 = annonceDao.save(new Annonce("a1"));
        Annonce a2 = annonceDao.save(new Annonce("a2"));
        Annonce a3 = annonceDao.save(new Annonce("a3"));
        Annonce a4 = annonceDao.save(new Annonce("a4"));
        System.out.println(a1.getId());
        System.out.println(a2.getId());
        System.out.println(a3.getId());
        System.out.println(a4.getId());
    }
}
package org.klab.demos.p6spy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.klab.mypc.domain.Produit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "/p6spy-applicationContext.xml",
    "/p6spy-test-applicationContext.xml", 
})
public class P6SpyTest extends TestCase {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Test
    @Transactional
    public void test() {
        entityManager.createQuery("from Produit").getResultList();
        entityManager.persist(new Produit("nom du produit"));
    }
}

package org.klab.demos.hades.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.klab.demos.hades.domain.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jpa.AbstractJpaTests;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "/hades-applicationContext.xml"
})
@Transactional
public class ProduitDaoTest extends AbstractJpaTests {
    
    @Autowired
    private ProduitDao produitDao;
    
    @Test
    public void test() {
        String nom = "p1";
        
        Produit p = new Produit();
        p.setNom(nom);
        p = produitDao.save(p);
        
        assertNotNull(p.getId());
        assertNotNull(produitDao.findByNom(nom));
        assertEquals(nom, produitDao.findByNom(nom).getNom());
    }
}

package org.klab.demos.hades.dao;

import org.klab.demos.hades.domain.Produit;
import org.synyx.hades.dao.GenericDao;

public interface ProduitDao extends GenericDao<Produit, Long> {
	
	Produit findByNom(String nom);
}

package org.klab.demos.hades.domain;

import javax.persistence.Entity;

import org.synyx.hades.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Produit extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private String nom;
}

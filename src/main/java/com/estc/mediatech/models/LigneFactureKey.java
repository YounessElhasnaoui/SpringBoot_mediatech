package com.estc.mediatech.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;



@Getter
@Setter
@Embeddable
public class LigneFactureKey implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4722352116317893282L;


	@Column(name="facture_id")
	private Integer factureId;

	@Column(name="produit_id")
	private Integer produitId;

}



package com.estc.mediatech.models;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="facture")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class FactureEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@Column(nullable=false)
	private String ref;
	
	@Column(nullable=false,name="date_creation_facture")
	private Date date;
	
	@ManyToOne
	private ClientEntity client;
}

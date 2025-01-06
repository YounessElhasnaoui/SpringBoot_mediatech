package com.estc.mediatech.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = false)
	private String nom;
	
	@Column(nullable = false)
	private String prenom;
	
	@Column(name = "client_telephone")
	private String telephone;
	
	@OneToMany(mappedBy ="client", cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
	
	//@JoinColumn(name = "client-id")
	private List<FactureEntity> factures;

	public void setId(Integer id)
	{this.id=id;}
}

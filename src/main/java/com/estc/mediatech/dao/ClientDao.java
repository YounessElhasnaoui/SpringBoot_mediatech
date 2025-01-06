package com.estc.mediatech.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estc.mediatech.models.ClientEntity;

@Repository
public interface ClientDao extends JpaRepository<ClientEntity,Integer> {
	ClientEntity findByNom(String nom);
}

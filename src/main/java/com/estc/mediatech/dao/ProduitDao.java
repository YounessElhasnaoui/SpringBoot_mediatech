package com.estc.mediatech.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.estc.mediatech.models.ProduitEntity;

import java.util.Optional;

@Repository
public interface ProduitDao extends JpaRepository<ProduitEntity, Integer> {
    Optional<ProduitEntity> findByRef(String ref);
}

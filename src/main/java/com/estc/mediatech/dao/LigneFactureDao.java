package com.estc.mediatech.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.estc.mediatech.models.LigneFactureEntity;
import com.estc.mediatech.models.LigneFactureKey;

@Repository
public interface LigneFactureDao extends JpaRepository<LigneFactureEntity, LigneFactureKey> {

    // If you need custom queries, add them here.
    // e.g. List<LigneFactureEntity> findByFactureId(Integer factureId);
    // or List<LigneFactureEntity> findByProduitId(Integer produitId);
}

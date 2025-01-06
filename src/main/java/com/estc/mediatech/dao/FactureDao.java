package com.estc.mediatech.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.estc.mediatech.models.FactureEntity;
import java.util.Optional;

@Repository
public interface FactureDao extends JpaRepository<FactureEntity, Integer> {
    Optional<FactureEntity> findByRef(String ref);
}

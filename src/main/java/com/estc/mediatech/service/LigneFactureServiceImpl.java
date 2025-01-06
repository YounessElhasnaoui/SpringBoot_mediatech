package com.estc.mediatech.service;

import com.estc.mediatech.dao.LigneFactureDao;
import com.estc.mediatech.dao.FactureDao;
import com.estc.mediatech.dao.ProduitDao;
import com.estc.mediatech.dto.LigneFactureRequestDto;
import com.estc.mediatech.dto.LigneFactureResponseDto;
import com.estc.mediatech.exception.EntityNotFoundException;
import com.estc.mediatech.models.FactureEntity;
import com.estc.mediatech.models.LigneFactureEntity;
import com.estc.mediatech.models.LigneFactureKey;
import com.estc.mediatech.models.ProduitEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LigneFactureServiceImpl implements LigneFactureService {

    private final LigneFactureDao ligneFactureDao;
    private final FactureDao factureDao;
    private final ProduitDao produitDao;
    private final ModelMapper modelMapper;

    public LigneFactureServiceImpl(LigneFactureDao ligneFactureDao,
                                   FactureDao factureDao,
                                   ProduitDao produitDao,
                                   ModelMapper modelMapper) {
        this.ligneFactureDao = ligneFactureDao;
        this.factureDao = factureDao;
        this.produitDao = produitDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public LigneFactureResponseDto create(LigneFactureRequestDto dto) {
        // 1) Validate Facture
        FactureEntity factureEntity = factureDao.findById(dto.getFactureId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Facture not found with ID: " + dto.getFactureId()));

        // 2) Validate Produit
        ProduitEntity produitEntity = produitDao.findById(dto.getProduitId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produit not found with ID: " + dto.getProduitId()));

        // 3) Build Composite Key
        LigneFactureKey key = new LigneFactureKey();
        key.setFactureId(dto.getFactureId());
        key.setProduitId(dto.getProduitId());

        // 4) Create and populate Entity
        LigneFactureEntity ligneFactureEntity = new LigneFactureEntity();
        ligneFactureEntity.setId(key);
        ligneFactureEntity.setFacture(factureEntity);
        ligneFactureEntity.setProduit(produitEntity);
        ligneFactureEntity.setQuantite(dto.getQuantite());

        // 5) Save
        LigneFactureEntity savedEntity = ligneFactureDao.save(ligneFactureEntity);

        // 6) Map to Response
        return entityToResponseDto(savedEntity);
    }

    @Override
    public LigneFactureResponseDto findById(Integer factureId, Integer produitId) {
        LigneFactureKey key = new LigneFactureKey();
        key.setFactureId(factureId);
        key.setProduitId(produitId);

        LigneFactureEntity entity = ligneFactureDao.findById(key)
                .orElseThrow(() -> new EntityNotFoundException(
                        "LigneFacture not found with Facture ID: " + factureId +
                                " and Produit ID: " + produitId));

        return entityToResponseDto(entity);
    }

    @Override
    public List<LigneFactureResponseDto> findAll() {
        return ligneFactureDao.findAll().stream()
                .map(this::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public LigneFactureResponseDto update(LigneFactureRequestDto dto, Integer factureId, Integer produitId) {
        LigneFactureKey key = new LigneFactureKey();
        key.setFactureId(factureId);
        key.setProduitId(produitId);

        // 1) Fetch existing line
        Optional<LigneFactureEntity> optionalLine = ligneFactureDao.findById(key);
        if (optionalLine.isEmpty()) {
            throw new EntityNotFoundException("LigneFacture not found with Facture ID: " + factureId +
                    " and Produit ID: " + produitId);
        }

        // 2) Optionally verify Facture and Produit again if needed
        //    (especially if the user might want to move to a different produit, etc.)
        //    But typically, for an update, the composite key is fixed, so we just update quantite.

        // 3) Update the existing line
        LigneFactureEntity existingLine = optionalLine.get();
        existingLine.setQuantite(dto.getQuantite());

        // 4) Save
        LigneFactureEntity updatedEntity = ligneFactureDao.save(existingLine);

        // 5) Map to response
        return entityToResponseDto(updatedEntity);
    }

    @Override
    public void delete(Integer factureId, Integer produitId) {
        LigneFactureKey key = new LigneFactureKey();
        key.setFactureId(factureId);
        key.setProduitId(produitId);

        if (!ligneFactureDao.existsById(key)) {
            throw new EntityNotFoundException("Cannot delete. LigneFacture with Facture ID: " +
                    factureId + " and Produit ID: " + produitId + " does not exist.");
        }

        ligneFactureDao.deleteById(key);
    }

    // Helper method to convert entity -> response DTO
    private LigneFactureResponseDto entityToResponseDto(LigneFactureEntity entity) {
        LigneFactureResponseDto dto = new LigneFactureResponseDto();
        dto.setFactureId(entity.getId().getFactureId());
        dto.setProduitId(entity.getId().getProduitId());
        dto.setQuantite(entity.getQuantite());
        return dto;
    }
}

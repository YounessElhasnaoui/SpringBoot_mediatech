package com.estc.mediatech.service;

import com.estc.mediatech.dao.ProduitDao;
import com.estc.mediatech.dto.ProduitRequestDto;
import com.estc.mediatech.dto.ProduitResponseDto;
import com.estc.mediatech.exception.EntityNotFoundException;
import com.estc.mediatech.models.ProduitEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitDao produitDao;
    private final ModelMapper modelMapper;

    public ProduitServiceImpl(ProduitDao produitDao, ModelMapper modelMapper) {
        this.produitDao = produitDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProduitResponseDto save(ProduitRequestDto produitRequestDto) {
        // Convert DTO -> Entity
        ProduitEntity produitEntity = modelMapper.map(produitRequestDto, ProduitEntity.class);
        // Save
        ProduitEntity saved = produitDao.save(produitEntity);
        // Convert Entity -> DTO
        return modelMapper.map(saved, ProduitResponseDto.class);
    }

    @Override
    public ProduitResponseDto findById(Integer id) {
        ProduitEntity produitEntity = produitDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit not found with ID: " + id));
        return modelMapper.map(produitEntity, ProduitResponseDto.class);
    }

    @Override
    public List<ProduitResponseDto> findAll() {
        return produitDao.findAll().stream()
                .map(entity -> modelMapper.map(entity, ProduitResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProduitResponseDto update(ProduitRequestDto produitRequestDto, Integer id) {
        Optional<ProduitEntity> optionalProduit = produitDao.findById(id);
        if (optionalProduit.isEmpty()) {
            throw new EntityNotFoundException("Produit not found with ID: " + id);
        }

        // Map new data to the existing Entity
        ProduitEntity produitEntity = modelMapper.map(produitRequestDto, ProduitEntity.class);
        // Preserve the ID
        produitEntity.setId(id);

        // Save updated
        ProduitEntity updated = produitDao.save(produitEntity);
        // Convert back to DTO
        return modelMapper.map(updated, ProduitResponseDto.class);
    }

    @Override
    public void delete(Integer id) {
        produitDao.deleteById(id);
    }

    @Override
    public ProduitResponseDto findByRef(String ref) {
        Optional<ProduitEntity> optionalProduit = produitDao.findByRef(ref);
        ProduitEntity produitEntity = optionalProduit
                .orElseThrow(() -> new EntityNotFoundException("Produit not found with ref: " + ref));
        return modelMapper.map(produitEntity, ProduitResponseDto.class);
    }
}

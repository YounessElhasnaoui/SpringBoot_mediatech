package com.estc.mediatech.service;

import com.estc.mediatech.dto.ProduitRequestDto;
import com.estc.mediatech.dto.ProduitResponseDto;
import java.util.List;

public interface ProduitService {

    ProduitResponseDto save(ProduitRequestDto produitRequestDto);

    ProduitResponseDto findById(Integer id);

    List<ProduitResponseDto> findAll();

    ProduitResponseDto update(ProduitRequestDto produitRequestDto, Integer id);

    void delete(Integer id);

    // Optional: find by ref if needed
    ProduitResponseDto findByRef(String ref);
}

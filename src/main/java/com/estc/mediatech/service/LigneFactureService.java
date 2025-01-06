package com.estc.mediatech.service;

import com.estc.mediatech.dto.LigneFactureRequestDto;
import com.estc.mediatech.dto.LigneFactureResponseDto;
import java.util.List;

public interface LigneFactureService {

    LigneFactureResponseDto create(LigneFactureRequestDto dto);

    LigneFactureResponseDto findById(Integer factureId, Integer produitId);

    List<LigneFactureResponseDto> findAll();

    LigneFactureResponseDto update(LigneFactureRequestDto dto, Integer factureId, Integer produitId);

    void delete(Integer factureId, Integer produitId);
}

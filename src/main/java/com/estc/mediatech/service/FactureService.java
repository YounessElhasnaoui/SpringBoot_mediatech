package com.estc.mediatech.service;

import com.estc.mediatech.dto.FactureRequestDto;
import com.estc.mediatech.dto.FactureResponseDto;
import java.util.List;

public interface FactureService {

    FactureResponseDto save(FactureRequestDto factureRequestDto);

    FactureResponseDto findById(Integer id);

    List<FactureResponseDto> findAll();

    FactureResponseDto update(FactureRequestDto factureRequestDto, Integer id);

    void delete(Integer id);

    FactureResponseDto findByRef(String ref);

}

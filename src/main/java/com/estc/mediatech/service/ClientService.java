package com.estc.mediatech.service;

import java.util.List;

import com.estc.mediatech.dto.ClientRequestDto;
import com.estc.mediatech.dto.ClientResponseDto;

public interface ClientService {
	
	ClientResponseDto save(ClientRequestDto clientRequestDto);
	
	ClientResponseDto findById(Integer id);
	
	ClientResponseDto finByNom(String nom);
	
	void delete(Integer id);
	
	ClientResponseDto update(ClientRequestDto clientRequestDto, Integer id);

	ClientResponseDto findByNom(String nom);
	
	 List<ClientResponseDto> findAll();
	
	
}

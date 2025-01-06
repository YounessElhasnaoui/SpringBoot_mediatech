package com.estc.mediatech.service;

import com.estc.mediatech.dao.FactureDao;
import com.estc.mediatech.dao.ClientDao;
import com.estc.mediatech.dto.FactureRequestDto;
import com.estc.mediatech.dto.FactureResponseDto;
import com.estc.mediatech.exception.EntityNotFoundException;
import com.estc.mediatech.models.ClientEntity;
import com.estc.mediatech.models.FactureEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FactureServiceImpl implements FactureService {

    private final FactureDao factureDao;
    private final ClientDao clientDao;      // To link Facture to existing Client
    private final ModelMapper modelMapper;

    public FactureServiceImpl(FactureDao factureDao, ClientDao clientDao, ModelMapper modelMapper) {
        this.factureDao = factureDao;
        this.clientDao = clientDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public FactureResponseDto save(FactureRequestDto factureRequestDto) {
        // Map DTO -> Entity
        FactureEntity factureEntity = modelMapper.map(factureRequestDto, FactureEntity.class);

        // Retrieve client by ID from request DTO
        if (factureRequestDto.getClientId() != null) {
            ClientEntity clientEntity = clientDao.findById(factureRequestDto.getClientId())
                    .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: "
                            + factureRequestDto.getClientId()));
            factureEntity.setClient(clientEntity);
        }

        // Save to DB
        FactureEntity savedEntity = factureDao.save(factureEntity);

        // Convert back to DTO
        FactureResponseDto responseDto = modelMapper.map(savedEntity, FactureResponseDto.class);
        // Ensure we return the clientId
        if (savedEntity.getClient() != null) {
            responseDto.setClientId(savedEntity.getClient().getId());
        }
        return responseDto;
    }

    @Override
    public FactureResponseDto findById(Integer id) {
        FactureEntity entity = factureDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Facture not found with ID: " + id));

        FactureResponseDto responseDto = modelMapper.map(entity, FactureResponseDto.class);
        if (entity.getClient() != null) {
            responseDto.setClientId(entity.getClient().getId());
        }
        return responseDto;
    }

    @Override
    public List<FactureResponseDto> findAll() {
        return factureDao.findAll().stream()
                .map(entity -> {
                    FactureResponseDto dto = modelMapper.map(entity, FactureResponseDto.class);
                    if (entity.getClient() != null) {
                        dto.setClientId(entity.getClient().getId());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public FactureResponseDto update(FactureRequestDto factureRequestDto, Integer id) {
        Optional<FactureEntity> optionalFacture = factureDao.findById(id);
        if (optionalFacture.isEmpty()) {
            throw new EntityNotFoundException("Facture not found with ID: " + id);
        }

        // Map new data
        FactureEntity factureEntity = modelMapper.map(factureRequestDto, FactureEntity.class);
        factureEntity.setId(id); // preserve the ID

        // Attach the client
        if (factureRequestDto.getClientId() != null) {
            ClientEntity clientEntity = clientDao.findById(factureRequestDto.getClientId())
                    .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: "
                            + factureRequestDto.getClientId()));
            factureEntity.setClient(clientEntity);
        }

        // Save updated Facture
        FactureEntity updatedEntity = factureDao.save(factureEntity);

        // Map to response
        FactureResponseDto responseDto = modelMapper.map(updatedEntity, FactureResponseDto.class);
        if (updatedEntity.getClient() != null) {
            responseDto.setClientId(updatedEntity.getClient().getId());
        }
        return responseDto;
    }

    @Override
    public void delete(Integer id) {
        factureDao.deleteById(id);
    }

    @Override
    public FactureResponseDto findByRef(String ref) {
        Optional<FactureEntity> optionalFacture = factureDao.findByRef(ref);
        FactureEntity factureEntity = optionalFacture.orElseThrow(
                () -> new EntityNotFoundException("Facture not found with ref: " + ref)
        );

        // Convert to DTO
        FactureResponseDto responseDto = modelMapper.map(factureEntity, FactureResponseDto.class);
        if (factureEntity.getClient() != null) {
            responseDto.setClientId(factureEntity.getClient().getId());
        }

        return responseDto;
    }
}

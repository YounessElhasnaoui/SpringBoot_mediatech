package com.estc.mediatech.controllers;

import com.estc.mediatech.dto.FactureRequestDto;
import com.estc.mediatech.dto.FactureResponseDto;
import com.estc.mediatech.exception.EntityNotFoundException;
import com.estc.mediatech.service.FactureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("factures")
public class FactureController {

    private final FactureService factureService;

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @GetMapping("")
    public ResponseEntity<List<FactureResponseDto>> getAllFactures() {
        List<FactureResponseDto> factureList = factureService.findAll();
        return ResponseEntity.ok(factureList);
    }

    @PostMapping("")
    public ResponseEntity<FactureResponseDto> createFacture(@RequestBody FactureRequestDto factureRequestDto) {
        FactureResponseDto created = factureService.save(factureRequestDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureResponseDto> getFactureById(@PathVariable("id") Integer id) {
        FactureResponseDto factureResponse = factureService.findById(id);
        return ResponseEntity.ok(factureResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactureResponseDto> updateFacture(
            @RequestBody FactureRequestDto factureRequestDto,
            @PathVariable("id") Integer id) throws EntityNotFoundException {

        FactureResponseDto updated = factureService.update(factureRequestDto, id);
        return ResponseEntity.accepted().body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable("id") Integer id) {
        factureService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ref/{ref}")
    public ResponseEntity<FactureResponseDto> getFactureByRef(@PathVariable("ref") String ref) {
        FactureResponseDto factureResponse = factureService.findByRef(ref);
        return ResponseEntity.ok(factureResponse);
    }
}

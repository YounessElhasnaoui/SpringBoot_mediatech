package com.estc.mediatech.controllers;

import com.estc.mediatech.dto.LigneFactureRequestDto;
import com.estc.mediatech.dto.LigneFactureResponseDto;
import com.estc.mediatech.service.LigneFactureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ligne-factures")
public class LigneFactureController {

    private final LigneFactureService ligneFactureService;

    public LigneFactureController(LigneFactureService ligneFactureService) {
        this.ligneFactureService = ligneFactureService;
    }

    // 1) GET /ligne-factures
    @GetMapping("")
    public ResponseEntity<List<LigneFactureResponseDto>> getAllLignes() {
        List<LigneFactureResponseDto> lignes = ligneFactureService.findAll();
        return ResponseEntity.ok(lignes);
    }

    // 2) POST /ligne-factures
    @PostMapping("")
    public ResponseEntity<LigneFactureResponseDto> createLigne(@RequestBody LigneFactureRequestDto dto) {
        LigneFactureResponseDto created = ligneFactureService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // 3) GET /ligne-factures/{factureId}/{produitId}
    @GetMapping("/{factureId}/{produitId}")
    public ResponseEntity<LigneFactureResponseDto> getLigneById(
            @PathVariable("factureId") Integer factureId,
            @PathVariable("produitId") Integer produitId) {

        LigneFactureResponseDto response = ligneFactureService.findById(factureId, produitId);
        return ResponseEntity.ok(response);
    }

    // 4) PUT /ligne-factures/{factureId}/{produitId}
    @PutMapping("/{factureId}/{produitId}")
    public ResponseEntity<LigneFactureResponseDto> updateLigne(
            @RequestBody LigneFactureRequestDto dto,
            @PathVariable("factureId") Integer factureId,
            @PathVariable("produitId") Integer produitId) {

        LigneFactureResponseDto updated = ligneFactureService.update(dto, factureId, produitId);
        return ResponseEntity.accepted().body(updated);
    }

    // 5) DELETE /ligne-factures/{factureId}/{produitId}
    @DeleteMapping("/{factureId}/{produitId}")
    public ResponseEntity<Void> deleteLigne(
            @PathVariable("factureId") Integer factureId,
            @PathVariable("produitId") Integer produitId) {

        ligneFactureService.delete(factureId, produitId);
        return ResponseEntity.noContent().build();
    }
}

package com.estc.mediatech.controllers;

import com.estc.mediatech.dto.ProduitRequestDto;
import com.estc.mediatech.dto.ProduitResponseDto;
import com.estc.mediatech.service.ProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produits")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProduitResponseDto>> getAllProduits() {
        List<ProduitResponseDto> produits = produitService.findAll();
        return ResponseEntity.ok(produits);
    }

    @PostMapping("")
    public ResponseEntity<ProduitResponseDto> createProduit(@RequestBody ProduitRequestDto produitRequestDto) {
        ProduitResponseDto created = produitService.save(produitRequestDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitResponseDto> getProduitById(@PathVariable("id") Integer id) {
        ProduitResponseDto produit = produitService.findById(id);
        return ResponseEntity.ok(produit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitResponseDto> updateProduit(
            @RequestBody ProduitRequestDto produitRequestDto,
            @PathVariable("id") Integer id) {

        ProduitResponseDto updated = produitService.update(produitRequestDto, id);
        return ResponseEntity.accepted().body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable("id") Integer id) {
        produitService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Optional: Endpoint to find by ref
    @GetMapping("/ref/{ref}")
    public ResponseEntity<ProduitResponseDto> getProduitByRef(@PathVariable("ref") String ref) {
        ProduitResponseDto produit = produitService.findByRef(ref);
        return ResponseEntity.ok(produit);
    }
}

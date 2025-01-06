package com.estc.mediatech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Request DTO for creating/updating a product (Produit)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitRequestDto {

    private String ref;
    private String libelle;
    private BigDecimal prix;
    private double quantit_stock;
}

package com.estc.mediatech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Response DTO for Product (Produit)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitResponseDto {

    private Integer id;
    private String ref;
    private String libelle;
    private BigDecimal prix;
    private double quantit_stock;
}

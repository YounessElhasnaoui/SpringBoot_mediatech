package com.estc.mediatech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for LigneFacture.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneFactureResponseDto {

    private Integer factureId;
    private Integer produitId;
    private double quantite;
}

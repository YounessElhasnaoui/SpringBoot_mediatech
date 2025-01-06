package com.estc.mediatech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for creating/updating a LigneFacture.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneFactureRequestDto {

    private Integer factureId;
    private Integer produitId;
    private double quantite;
}

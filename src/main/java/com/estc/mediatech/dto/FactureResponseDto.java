package com.estc.mediatech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Response DTO for Facture
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactureResponseDto {

    private Integer id;
    private String ref;
    private Date date;
    private Integer clientId;  // Optionally return the linked Client ID

    // Alternatively, you could include a nested object or partial client data
    // private ClientResponseDto client;
}

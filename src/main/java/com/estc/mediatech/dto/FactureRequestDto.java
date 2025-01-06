package com.estc.mediatech.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Request DTO for creating/updating Facture
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactureRequestDto {

    private String ref;
    private Date date;
    private Integer clientId;  // Link to existing ClientEntity by ID

}

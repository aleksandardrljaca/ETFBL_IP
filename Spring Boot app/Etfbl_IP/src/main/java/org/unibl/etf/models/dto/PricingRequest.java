package org.unibl.etf.models.dto;

import lombok.Data;

@Data
public class PricingRequest {
    private Integer minDistance;
    private Integer maxDistance;
    private Double price;
}

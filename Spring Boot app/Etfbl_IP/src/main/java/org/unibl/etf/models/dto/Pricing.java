package org.unibl.etf.models.dto;

import lombok.Data;

@Data
public class Pricing {
    private Integer id;
    private Integer minDistance;
    private Integer maxDistance;
    private Double price;
}

package org.unibl.etf.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MalfunctionsPerVehicle {
    private String vehicleId;
    private Long num;
}

package org.unibl.etf.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequest{
    private String id;
    private Date acquisitionDate;
    private Double acquisitionPrice;
    private Integer manufacturerByManufacturerIdId;
    private String model;
    private String description;
    private String vehicleType;
    private Integer maxSpeed;
    private Integer rangePerCharge;
    private Integer imageByImageIdId;
}
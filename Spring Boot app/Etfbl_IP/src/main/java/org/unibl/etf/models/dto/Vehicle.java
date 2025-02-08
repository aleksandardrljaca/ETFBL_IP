package org.unibl.etf.models.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.unibl.etf.models.enums.VehicleType;

import java.util.Date;

@Data
public class Vehicle {
    private String id;

    private Date acquisitionDate;

    private Double acquisitionPrice;

    private String model;

    private String description;

    private Integer rangePerCharge;

    private Integer maxSpeed;

    private VehicleType vehicleType;

    private String manufacturerByManufacturerIdName;

    private byte[] imageByImageIdImageData;
}


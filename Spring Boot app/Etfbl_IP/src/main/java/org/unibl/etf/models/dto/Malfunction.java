package org.unibl.etf.models.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;
@Data
public class Malfunction {
    private Integer id;
    private Date date;
    private Time time;
    private String description;
    private String vehicleByVehicleIdId;
    private String vehicleByVehicleIdManufacturerByManufacturerIdName;
    private String vehicleByVehicleIdModel;
}

package org.unibl.etf.models.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class MalfunctionRequest {
    private Date date;
    private Time time;
    private String description;
    private String vehicleByVehicleIdId;
}

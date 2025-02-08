package org.unibl.etf.models.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.unibl.etf.models.entities.ClientEntity;
import org.unibl.etf.models.entities.LocationEntity;
import org.unibl.etf.models.entities.VehicleEntity;

import java.sql.Time;
import java.util.Date;

@Data
public class Rent {
    private Integer id;

    private Date date;

    private Time startTime;

    private Time endTime;

    private Double price;

    private String paymentInfo;

    private String clientByClientIdFirstname;
    private String clientByClientIdLastname;
    private String locationByStartLocationName;
    private Double locationByStartLocationLongitude;
    private Double locationByStartLocationLatitude;
    private String locationByEndLocationName;
    private Double locationByEndLocationLongitude;
    private Double locationByEndLocationLatitude;
    private String vehicleByVehicleIdId;
    private String vehicleByVehicleIdManufacturerByManufacturerIdName;
    private String vehicleByVehicleIdModel;
}

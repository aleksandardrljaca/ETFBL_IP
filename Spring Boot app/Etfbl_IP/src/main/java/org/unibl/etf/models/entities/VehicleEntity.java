package org.unibl.etf.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.unibl.etf.models.enums.VehicleType;

import java.sql.Date;
import java.util.Objects;
@Data
@Entity
@Table(name = "vehicle", schema = "etfbl_ip", catalog = "")
public class VehicleEntity {

    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "acquisition_date")
    private Date acquisitionDate;
    @Basic
    @Column(name = "acquisition_price")
    private Double acquisitionPrice;
    @Basic
    @Column(name = "model")
    private String model;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "range_per_charge")
    private Integer rangePerCharge;
    @Basic
    @Column(name = "max_speed")
    private Integer maxSpeed;
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type")
    private VehicleType vehicleType;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id", nullable = false)
    private ManufacturerEntity manufacturerByManufacturerId;
    @ManyToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id", nullable = false)
    private ImageEntity imageByImageId;


}

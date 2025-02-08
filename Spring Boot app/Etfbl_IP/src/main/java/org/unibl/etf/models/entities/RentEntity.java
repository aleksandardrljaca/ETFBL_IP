package org.unibl.etf.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "rent", schema = "etfbl_ip", catalog = "")
public class RentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "start_time")
    private Time startTime;
    @Basic
    @Column(name = "end_time")
    private Time endTime;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "payment_info")
    private String paymentInfo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private ClientEntity clientByClientId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_location", referencedColumnName = "id", nullable = false)
    private LocationEntity locationByStartLocation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_location", referencedColumnName = "id", nullable = false)
    private LocationEntity locationByEndLocation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false)
    private VehicleEntity vehicleByVehicleId;

}

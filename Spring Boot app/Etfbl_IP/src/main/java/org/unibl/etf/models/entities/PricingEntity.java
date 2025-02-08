package org.unibl.etf.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@Entity
@Table(name = "pricing", schema = "etfbl_ip", catalog = "")
public class PricingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "min_distance")
    private Integer minDistance;
    @Basic
    @Column(name = "max_distance")
    private Integer maxDistance;
    @Basic
    @Column(name = "price")
    private Double price;

}

package org.unibl.etf.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "promotion", schema = "etfbl_ip", catalog = "")
public class PromotionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "valid_until")
    private Date validUntil;

}

package org.unibl.etf.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Arrays;
import java.util.Objects;
@Data
@Entity
@Table(name = "image", schema = "etfbl_ip", catalog = "")
public class ImageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Lob
    @Column(name = "image_data")
    private byte[] imageData;


}

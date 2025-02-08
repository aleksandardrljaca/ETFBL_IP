package org.unibl.etf.models.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class Promotion {
    private Integer id;
    private String title;
    private String description;
    private Date validUntil;
}

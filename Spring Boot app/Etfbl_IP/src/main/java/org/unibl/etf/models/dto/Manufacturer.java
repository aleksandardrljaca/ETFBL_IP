package org.unibl.etf.models.dto;


import lombok.Data;

@Data
public class Manufacturer {
    private Integer id;

    private String name;

    private String country;

    private String address;

    private String phone;

    private String fax;

    private String email;


}

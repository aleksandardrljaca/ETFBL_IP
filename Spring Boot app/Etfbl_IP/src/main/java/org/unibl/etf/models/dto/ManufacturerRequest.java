package org.unibl.etf.models.dto;

import lombok.Data;

@Data
public class ManufacturerRequest {
    private String name;

    private String country;

    private String address;

    private String phone;

    private String fax;

    private String email;
}

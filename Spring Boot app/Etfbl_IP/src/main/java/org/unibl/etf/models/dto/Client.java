package org.unibl.etf.models.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.unibl.etf.models.entities.ImageEntity;

@Data
public class Client {
    private Integer id;
    private String firstname;

    private String lastname;

    private String drivingLicense;

    private String idCard;

    private String email;

    private String phone;

    private String username;

    private String password;

    private Boolean isBlocked;
}

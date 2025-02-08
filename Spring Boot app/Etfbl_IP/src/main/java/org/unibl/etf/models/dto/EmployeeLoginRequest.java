package org.unibl.etf.models.dto;

import lombok.Data;

@Data
public class EmployeeLoginRequest {
    private String username;
    private String password;
}

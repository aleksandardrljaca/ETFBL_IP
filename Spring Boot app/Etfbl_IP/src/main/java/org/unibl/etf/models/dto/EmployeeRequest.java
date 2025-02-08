package org.unibl.etf.models.dto;

import lombok.Data;
import org.unibl.etf.models.enums.Role;

@Data
public class EmployeeRequest {

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String role;
}

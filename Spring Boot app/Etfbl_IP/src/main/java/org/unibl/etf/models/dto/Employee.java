package org.unibl.etf.models.dto;


import lombok.Data;
import org.unibl.etf.models.enums.Role;
@Data
public class Employee {

    private Integer id;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private Role role;
}

package org.unibl.etf.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.unibl.etf.models.enums.Role;

import java.util.Objects;

@Data
@Entity
@Table(name = "employee", schema = "etfbl_ip", catalog = "")
public class EmployeeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "firstname")
    private String firstname;
    @Basic
    @Column(name = "lastname")
    private String lastname;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

}

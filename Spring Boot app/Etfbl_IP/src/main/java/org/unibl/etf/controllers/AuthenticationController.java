package org.unibl.etf.controllers;

import org.springframework.web.bind.annotation.*;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Employee;
import org.unibl.etf.models.dto.EmployeeLoginRequest;
import org.unibl.etf.services.AuthenticationService;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }
    @PostMapping()
    public Employee login(@RequestBody EmployeeLoginRequest request) throws NotFoundException {
        return service.login(request);
    }
}

package org.unibl.etf.services;

import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Employee;
import org.unibl.etf.models.dto.EmployeeLoginRequest;

public interface AuthenticationService {
    Employee login(EmployeeLoginRequest request) throws NotFoundException;
}

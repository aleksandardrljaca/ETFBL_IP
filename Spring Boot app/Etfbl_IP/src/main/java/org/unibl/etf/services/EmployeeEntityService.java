package org.unibl.etf.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Employee;
import org.unibl.etf.models.dto.EmployeeLoginRequest;
import org.unibl.etf.models.dto.EmployeeRequest;

import java.util.List;

public interface EmployeeEntityService {
    Employee insert(EmployeeRequest request);
    Employee update(Integer id,EmployeeRequest request);
    List<Employee> getAll();
    void delete(Integer id);

}

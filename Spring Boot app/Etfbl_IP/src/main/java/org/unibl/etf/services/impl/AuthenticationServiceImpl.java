package org.unibl.etf.services.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Employee;
import org.unibl.etf.models.dto.EmployeeLoginRequest;
import org.unibl.etf.models.entities.EmployeeEntity;
import org.unibl.etf.repositories.EmployeeEntityRepository;
import org.unibl.etf.services.AuthenticationService;
@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    private final EmployeeEntityRepository employeeEntityRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    public AuthenticationServiceImpl(EmployeeEntityRepository employeeEntityRepository, BCryptPasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.employeeEntityRepository = employeeEntityRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public Employee login(EmployeeLoginRequest request) throws NotFoundException {
        EmployeeEntity entity=employeeEntityRepository.findEmployeeEntitiesByUsername(request.getUsername()).orElseThrow(NotFoundException::new);
        if(passwordEncoder.matches(request.getPassword(),entity.getPassword()))
            return modelMapper.map(entity,Employee.class);
        else throw new NotFoundException();
    }
}

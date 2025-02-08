package org.unibl.etf.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Employee;
import org.unibl.etf.models.dto.EmployeeLoginRequest;
import org.unibl.etf.models.dto.EmployeeRequest;
import org.unibl.etf.models.entities.EmployeeEntity;
import org.unibl.etf.models.enums.Role;
import org.unibl.etf.repositories.EmployeeEntityRepository;
import org.unibl.etf.services.EmployeeEntityService;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class EmployeeEntityServiceImpl implements EmployeeEntityService {
    private final EmployeeEntityRepository repository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    @PersistenceContext
    private EntityManager entityManager;

    public EmployeeEntityServiceImpl(EmployeeEntityRepository repository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Employee insert(EmployeeRequest request) {
        EmployeeEntity entity=modelMapper.map(request,EmployeeEntity.class);
        entity.setId(null);
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        entity.setRole(Role.valueOf(request.getRole()));
        entity=repository.saveAndFlush(entity);
        entityManager.refresh(entity);
        return modelMapper.map(entity,Employee.class);
    }

    @Override
    public Employee update(Integer id,EmployeeRequest request) {
        EmployeeEntity entity=modelMapper.map(request,EmployeeEntity.class);
        entity.setId(id);
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        entity.setRole(Role.valueOf(request.getRole()));
        entity=repository.saveAndFlush(entity);
        entityManager.refresh(entity);
        return modelMapper.map(entity,Employee.class);
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll().stream().map(e->modelMapper.map(e,Employee.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}

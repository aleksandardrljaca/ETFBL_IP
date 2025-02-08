package org.unibl.etf.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.models.dto.Manufacturer;
import org.unibl.etf.models.dto.ManufacturerRequest;
import org.unibl.etf.models.entities.ManufacturerEntity;
import org.unibl.etf.repositories.ManufacturerEntityRepository;
import org.unibl.etf.services.ManufacturerEntityService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ManufacturerEntityServiceImpl implements ManufacturerEntityService {
    private final ManufacturerEntityRepository repository;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    public ManufacturerEntityServiceImpl(ManufacturerEntityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Manufacturer> getAll() {
        return repository.findAll().stream().map(m->modelMapper.map(m,Manufacturer.class)).collect(Collectors.toList());
    }

    @Override
    public Manufacturer insert(ManufacturerRequest request) {
        ManufacturerEntity entity=modelMapper.map(request,ManufacturerEntity.class);
        entity.setId(null);
        entity=repository.saveAndFlush(entity);
        entityManager.refresh(entity);
        return modelMapper.map(entity,Manufacturer.class);
    }

    @Override
    public Manufacturer update(Integer id, ManufacturerRequest request) {
        ManufacturerEntity entity=modelMapper.map(request,ManufacturerEntity.class);
        entity.setId(id);
        entity=repository.saveAndFlush(entity);
        entityManager.refresh(entity);
        return modelMapper.map(entity,Manufacturer.class);
    }

    @Override
    public void delete(Integer id){
       repository.deleteById(id);
    }

}

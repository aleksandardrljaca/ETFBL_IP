package org.unibl.etf.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Malfunction;
import org.unibl.etf.models.dto.MalfunctionRequest;
import org.unibl.etf.models.dto.MalfunctionsPerVehicle;
import org.unibl.etf.models.entities.MalfunctionEntity;
import org.unibl.etf.repositories.MalfunctionEntityRepository;
import org.unibl.etf.repositories.VehicleEntityRepository;
import org.unibl.etf.services.MalfunctionEntityService;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class MalfunctionEntityServiceImpl implements MalfunctionEntityService {
    private final MalfunctionEntityRepository repository;
    private final VehicleEntityRepository vehicleEntityRepository;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;
    public MalfunctionEntityServiceImpl(MalfunctionEntityRepository repository, VehicleEntityRepository vehicleEntityRepository, ModelMapper modelMapper) {
        this.repository = repository;
        this.vehicleEntityRepository = vehicleEntityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Malfunction insert(MalfunctionRequest request) throws NotFoundException {
        MalfunctionEntity entity=new MalfunctionEntity();
        entity.setId(null);
        entity.setDate(request.getDate());
        entity.setTime(request.getTime());
        entity.setDescription(request.getDescription());
        entity.setVehicleByVehicleId(vehicleEntityRepository.findById(request.getVehicleByVehicleIdId()).orElseThrow(NotFoundException::new));
        entity=repository.saveAndFlush(entity);
        entityManager.refresh(entity);
        return modelMapper.map(entity,Malfunction.class);
    }

    @Override
    public List<Malfunction> getAll() {
        return repository.findAll().stream().map(m->modelMapper.map(m,Malfunction.class)).collect(Collectors.toList());
    }

    @Override
    public List<MalfunctionsPerVehicle> getNumberOfMalfunctionsPerVehicle() {
        return repository.getNumberOfMalfunctionsPerVehicle().stream().map(o->new MalfunctionsPerVehicle((String)o[0],(Long)o[1])).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}

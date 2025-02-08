package org.unibl.etf.services.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.models.dto.Rent;
import org.unibl.etf.models.dto.RentIncomePerDay;
import org.unibl.etf.models.dto.RentIncomePerVehicleType;
import org.unibl.etf.models.enums.VehicleType;
import org.unibl.etf.repositories.RentEntityRepository;
import org.unibl.etf.services.RentEntityService;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class RentEntityServiceImpl implements RentEntityService {
    private final RentEntityRepository repository;
    private final ModelMapper modelMapper;
    public RentEntityServiceImpl(RentEntityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Rent> getAll() {
        return repository.findAll().stream().map(r->modelMapper.map(r,Rent.class)).collect(Collectors.toList());
    }

    @Override
    public List<Rent> getCurrentRents() {
        return repository.findAllByEndTimeAfterCurrentTime().stream().map(r->modelMapper.map(r,Rent.class)).collect(Collectors.toList());
    }

    @Override
    public List<RentIncomePerDay> getRentIncomePerDay(Integer month) {
        return repository.rentIncomePerDay(month).stream().map(o->new RentIncomePerDay((Integer)o[0],(Double)o[1])).collect(Collectors.toList());
    }

    @Override
    public List<RentIncomePerVehicleType> getRentIncomePerVehicleType() {
        return repository.rentIncomePerVehicleTypes().stream().map(o->new RentIncomePerVehicleType((VehicleType)o[0],(Double)o[1])).collect(Collectors.toList());

    }
}

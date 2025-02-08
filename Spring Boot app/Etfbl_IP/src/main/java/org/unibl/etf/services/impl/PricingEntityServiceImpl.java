package org.unibl.etf.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.models.dto.Pricing;
import org.unibl.etf.models.dto.PricingRequest;
import org.unibl.etf.models.entities.PricingEntity;
import org.unibl.etf.repositories.PricingEntityRepository;
import org.unibl.etf.services.PricingEntityService;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class PricingEntityServiceImpl implements PricingEntityService {
    private final PricingEntityRepository repository;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;
    public PricingEntityServiceImpl(PricingEntityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Pricing insert(PricingRequest request) {
        PricingEntity entity=modelMapper.map(request,PricingEntity.class);
        entity.setId(null);
        entity=repository.saveAndFlush(entity);
        entityManager.refresh(entity);
        return modelMapper.map(entity,Pricing.class);
    }

    @Override
    public Pricing update(Integer id, PricingRequest request) {
        PricingEntity entity=modelMapper.map(request,PricingEntity.class);
        entity.setId(id);
        entity=repository.saveAndFlush(entity);
        entityManager.refresh(entity);
        return modelMapper.map(entity,Pricing.class);
    }


    @Override
    public List<Pricing> getAll() {
        return repository.findAll().stream().map(p->modelMapper.map(p,Pricing.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}

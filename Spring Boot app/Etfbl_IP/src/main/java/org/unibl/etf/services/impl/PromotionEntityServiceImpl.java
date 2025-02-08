package org.unibl.etf.services.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Promotion;
import org.unibl.etf.repositories.PromotionEntityRepository;
import org.unibl.etf.services.PromotionEntityService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PromotionEntityServiceImpl implements PromotionEntityService {
    private final PromotionEntityRepository repository;
    private final ModelMapper modelMapper;
    public PromotionEntityServiceImpl(PromotionEntityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Promotion> getAll() {
        return repository.findAll().stream().map(p->modelMapper.map(p,Promotion.class)).collect(Collectors.toList());
    }
    @Override
    public Promotion getById(Integer id) throws NotFoundException {
        return modelMapper.map(repository.findById(id).orElseThrow(NotFoundException::new),Promotion.class);
    }
}

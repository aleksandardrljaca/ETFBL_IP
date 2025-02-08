package org.unibl.etf.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.BlockClientRequest;
import org.unibl.etf.models.dto.Client;
import org.unibl.etf.models.entities.ClientEntity;
import org.unibl.etf.repositories.ClientEntityRepository;
import org.unibl.etf.services.ClientEntityService;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class ClientEntityServiceImpl implements ClientEntityService {
    private final ClientEntityRepository repository;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    public ClientEntityServiceImpl(ClientEntityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Client> getAll() {
        return repository.findAll().stream().map(c->modelMapper.map(c,Client.class)).collect(Collectors.toList());
    }

    @Override
    public boolean block(Integer id, BlockClientRequest request) throws NotFoundException {
        ClientEntity entity=repository.findById(id).orElseThrow(NotFoundException::new);
        entity.setId(id);
        entity.setIsBlocked(request.getBlock());
        entity=repository.saveAndFlush(entity);
        entityManager.refresh(entity);
        return true;
    }
}

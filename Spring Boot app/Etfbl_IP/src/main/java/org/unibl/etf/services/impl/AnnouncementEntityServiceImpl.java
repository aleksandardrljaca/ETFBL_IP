package org.unibl.etf.services.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Announcement;
import org.unibl.etf.repositories.AnnouncementEntityRepository;
import org.unibl.etf.services.AnnouncementEntityService;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class AnnouncementEntityServiceImpl implements AnnouncementEntityService {

    private final AnnouncementEntityRepository repository;
    private final ModelMapper modelMapper;
    public AnnouncementEntityServiceImpl(AnnouncementEntityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Announcement> getAll() {
        return repository.findAll().stream().map(a->modelMapper.map(a,Announcement.class)).collect(Collectors.toList());
    }

    @Override
    public Announcement getById(Integer id) throws NotFoundException {
        return modelMapper.map(repository.findById(id).orElseThrow(NotFoundException::new),Announcement.class);
    }
}

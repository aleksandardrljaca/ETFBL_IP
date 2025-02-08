package org.unibl.etf.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Image;
import org.unibl.etf.models.entities.ImageEntity;
import org.unibl.etf.repositories.ImageEntityRepository;
import org.unibl.etf.services.ImageEntityService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

@Service
@Transactional
public class ImageEntityServiceImpl implements ImageEntityService {
    private final ImageEntityRepository imageRepository;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    public ImageEntityServiceImpl(ImageEntityRepository imageRepository, ModelMapper modelMapper) {
        this.imageRepository = imageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Image insert(MultipartFile file) throws IOException {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setId(null);
        imageEntity.setName(file.getOriginalFilename());
        imageEntity.setImageData(file.getBytes());
        imageEntity=imageRepository.saveAndFlush(imageEntity);
        entityManager.refresh(imageEntity);
        return modelMapper.map(imageEntity,Image.class);
    }

    @Override
    public byte[] getById(Integer id) throws NotFoundException {
        ImageEntity imageEntity=imageRepository.findById(id).orElseThrow(NotFoundException::new);
        return imageEntity.getImageData();
    }

    @Override
    public void deleteById(Integer id) {
        imageRepository.deleteById(id);
    }
    @Override
    public Image insertByPath(String filePath) throws IOException {
        Path path= Path.of(filePath);
        File file = new File(path.toString());
        byte[] imageData;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            imageData= fileInputStream.readAllBytes();

        }
        ImageEntity imageEntity=new ImageEntity();
        imageEntity.setId(null);
        imageEntity.setName(file.getName());
        imageEntity.setImageData(imageData);
        imageEntity=imageRepository.saveAndFlush(imageEntity);
        entityManager.refresh(imageEntity);
        return modelMapper.map(imageEntity,Image.class);
    }

}

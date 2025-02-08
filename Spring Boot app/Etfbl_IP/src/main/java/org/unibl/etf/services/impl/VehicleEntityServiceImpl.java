package org.unibl.etf.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Image;
import org.unibl.etf.models.dto.Vehicle;
import org.unibl.etf.models.dto.VehicleRequest;
import org.unibl.etf.models.entities.ImageEntity;
import org.unibl.etf.models.entities.VehicleEntity;
import org.unibl.etf.models.enums.VehicleType;
import org.unibl.etf.repositories.ImageEntityRepository;
import org.unibl.etf.repositories.ManufacturerEntityRepository;
import org.unibl.etf.repositories.VehicleEntityRepository;
import org.unibl.etf.services.ImageEntityService;
import org.unibl.etf.services.VehicleEntityService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VehicleEntityServiceImpl implements VehicleEntityService {
    private final VehicleEntityRepository vehicleRepository;
    private final ImageEntityRepository imageEntityRepository;
    private final ImageEntityService imageEntityService;
    private final ManufacturerEntityRepository manufacturerEntityRepository;
    private final ModelMapper modelMapper;
    @Value("${default.image.id}")
    private String defaultImageId;
    @PersistenceContext
    private EntityManager entityManager;

    public VehicleEntityServiceImpl(VehicleEntityRepository vehicleRepository, ImageEntityRepository imageEntityRepository, ImageEntityService imageEntityService, ManufacturerEntityRepository manufacturerEntityRepository, ModelMapper modelMapper) {
        this.vehicleRepository = vehicleRepository;
        this.imageEntityRepository = imageEntityRepository;
        this.imageEntityService = imageEntityService;
        this.manufacturerEntityRepository = manufacturerEntityRepository;

        this.modelMapper = modelMapper;
    }

    @Override
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll().stream().map(v -> modelMapper.map(v, Vehicle.class)).collect(Collectors.toList());
    }
    @Override
    public Vehicle getById(String id) throws NotFoundException {
        return modelMapper.map(vehicleRepository.findById(id).orElseThrow(NotFoundException::new),Vehicle.class);
    }
    @Override
    public Vehicle insert(VehicleRequest request) throws NotFoundException {
        VehicleEntity vehicleEntity = modelMapper.map(request, VehicleEntity.class);
        vehicleEntity.setId(request.getId());
        vehicleEntity.setImageByImageId(imageEntityRepository.findById(request.getImageByImageIdId()).orElseThrow(NotFoundException::new));
        vehicleEntity.setVehicleType(VehicleType.valueOf(request.getVehicleType()));
        vehicleEntity = vehicleRepository.saveAndFlush(vehicleEntity);
        entityManager.refresh(vehicleEntity);
        return modelMapper.map(vehicleEntity, Vehicle.class);
    }

    @Override
    public Boolean insertFromCSV(MultipartFile file) throws IOException, NotFoundException {
        // csv contains vehicle properties, each vehicle is linked with default image
        String text = new String(file.getBytes(), StandardCharsets.UTF_8);
        String[] lines = text.split(System.lineSeparator());
        for (int i = 1; i < lines.length; i++) {
            System.out.println(lines[i]);
            VehicleEntity vehicleEntity = parseVehicle(lines[i]);
            vehicleEntity = vehicleRepository.saveAndFlush(vehicleEntity);
            entityManager.refresh(vehicleEntity);
        }
        return true;
    }

    @Override
    public Vehicle findById(String id) throws NotFoundException {
        return modelMapper.map(vehicleRepository.findById(id).orElseThrow(NotFoundException::new), Vehicle.class);
    }

    @Override
    public boolean exists(String id) {
        return vehicleRepository.existsById(id);
    }

    @Override
    public void delete(String id) throws NotFoundException {
        Integer imageId = ((VehicleEntity) vehicleRepository.findById(id).orElseThrow(NotFoundException::new)).getImageByImageId().getId();
        vehicleRepository.deleteById(id);
        imageEntityRepository.deleteById(imageId);
    }

    private VehicleEntity parseVehicle(String line) throws NotFoundException, IOException {
        String[] params = line.split(",");
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setId(params[0]);

        vehicle.setAcquisitionPrice(Double.parseDouble(params[2]));
        vehicle.setManufacturerByManufacturerId(manufacturerEntityRepository.findByName(params[3]).orElseThrow(NotFoundException::new));
        vehicle.setModel(params[4]);
        vehicle.setVehicleType(VehicleType.valueOf(params[5]));

        if(VehicleType.CAR.toString().equals(params[5])){
            vehicle.setAcquisitionDate(Date.valueOf(params[1]));
            vehicle.setDescription(params[6]);
        }
        if(VehicleType.ELECTRIC_SCOOTER.toString().equals(params[5]))
            vehicle.setMaxSpeed(Integer.parseInt(params[7]));
        if(VehicleType.ELECTRIC_BICYCLE.toString().equals(params[5]))
            vehicle.setRangePerCharge(Integer.parseInt(params[8]));

        vehicle.setImageByImageId(imageEntityRepository.findById(Integer.parseInt(defaultImageId)).orElseThrow(NotFoundException::new));
        return vehicle;
    }
}

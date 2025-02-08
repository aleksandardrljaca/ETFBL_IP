package org.unibl.etf.services;

import org.springframework.web.multipart.MultipartFile;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Vehicle;
import org.unibl.etf.models.dto.VehicleRequest;

import java.io.IOException;
import java.util.List;

public interface VehicleEntityService {
    List<Vehicle> getAll();
    Vehicle insert(VehicleRequest vehicle) throws NotFoundException;
    Boolean insertFromCSV(MultipartFile file) throws IOException, NotFoundException;
    Vehicle getById(String id) throws NotFoundException;
    void delete(String id) throws NotFoundException;
    Vehicle findById(String id) throws NotFoundException;
    boolean exists(String id);
}

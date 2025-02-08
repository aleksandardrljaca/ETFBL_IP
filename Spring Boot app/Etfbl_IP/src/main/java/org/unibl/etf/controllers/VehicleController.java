package org.unibl.etf.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.VehicleRequest;
import org.unibl.etf.models.dto.Vehicle;
import org.unibl.etf.services.VehicleEntityService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin
public class VehicleController {
    private final VehicleEntityService vehicleService;

    public VehicleController(VehicleEntityService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @PostMapping()
    public Vehicle insert(@RequestBody VehicleRequest request) throws NotFoundException {
        return vehicleService.insert(request);
    }
    @PostMapping("/upload-csv")
    public Boolean insertFromCSV(@RequestPart MultipartFile file) throws IOException, NotFoundException {
       return vehicleService.insertFromCSV(file);
    }
    @GetMapping()
    public List<Vehicle> getAll(){
        return vehicleService.getAll();
    }
    @GetMapping("/{id}")
    public Vehicle getById(@PathVariable String id) throws NotFoundException {
        return vehicleService.getById(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws NotFoundException{
        vehicleService.delete(id);
    }
}

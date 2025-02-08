package org.unibl.etf.controllers;

import org.springframework.web.bind.annotation.*;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Malfunction;
import org.unibl.etf.models.dto.MalfunctionRequest;
import org.unibl.etf.models.dto.MalfunctionsPerVehicle;
import org.unibl.etf.services.MalfunctionEntityService;

import java.util.List;

@RestController
@RequestMapping("/api/malfunctions")
@CrossOrigin(origins = "*")

public class MalfunctionController {
    private final MalfunctionEntityService service;

    public MalfunctionController(MalfunctionEntityService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Malfunction> getAll(){
        return service.getAll();
    }
    @PostMapping
    public Malfunction insert(@RequestBody  MalfunctionRequest request) throws NotFoundException {
        return service.insert(request);
    }
    @GetMapping("/total-per-type")
    public List<MalfunctionsPerVehicle> getNumberOfMalfunctionsPerVehicle(){
        return service.getNumberOfMalfunctionsPerVehicle();
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
}

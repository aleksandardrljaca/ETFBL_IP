package org.unibl.etf.controllers;

import org.springframework.web.bind.annotation.*;
import org.unibl.etf.models.dto.Manufacturer;
import org.unibl.etf.models.dto.ManufacturerRequest;
import org.unibl.etf.services.ManufacturerEntityService;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
@CrossOrigin
public class ManufacturerController {
    private final ManufacturerEntityService service;

    public ManufacturerController(ManufacturerEntityService service) {
        this.service = service;
    }
    @GetMapping()
    public List<Manufacturer> getAll(){
        return service.getAll();
    }
    @PostMapping()
    public Manufacturer insert(@RequestBody ManufacturerRequest request){
        return service.insert(request);
    }
    @PutMapping("/{id}")
    public Manufacturer update(@PathVariable Integer id,@RequestBody ManufacturerRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}

package org.unibl.etf.controllers;

import org.springframework.web.bind.annotation.*;
import org.unibl.etf.models.dto.Pricing;
import org.unibl.etf.models.dto.PricingRequest;
import org.unibl.etf.services.PricingEntityService;

import java.util.List;

@RestController
@RequestMapping("/api/pricing")
@CrossOrigin
public class PricingController {
    private final PricingEntityService service;

    public PricingController(PricingEntityService service) {
        this.service = service;
    }
    @GetMapping()
    public List<Pricing> getAll(){
        return service.getAll();
    }
    @PostMapping()
    public Pricing insert(@RequestBody PricingRequest request){
        return service.insert(request);
    }
    @PutMapping("/{id}")
    public Pricing update(@PathVariable Integer id,@RequestBody PricingRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
}

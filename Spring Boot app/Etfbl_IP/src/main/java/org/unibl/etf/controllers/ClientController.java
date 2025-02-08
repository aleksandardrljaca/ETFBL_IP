package org.unibl.etf.controllers;

import org.springframework.web.bind.annotation.*;
import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.BlockClientRequest;
import org.unibl.etf.models.dto.Client;
import org.unibl.etf.services.ClientEntityService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin
public class ClientController {
    private final ClientEntityService service;

    public ClientController(ClientEntityService service) {
        this.service = service;
    }
    @GetMapping()
    public List<Client> getAll(){
        return service.getAll();
    }
    @PutMapping("/{id}")
    public Boolean block(@PathVariable Integer id, @RequestBody BlockClientRequest request) throws NotFoundException {
        return service.block(id,request);
    }
}

package org.unibl.etf.controllers;

import org.springframework.web.bind.annotation.*;
import org.unibl.etf.models.dto.Employee;
import org.unibl.etf.models.dto.EmployeeRequest;
import org.unibl.etf.services.EmployeeEntityService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {
    private final EmployeeEntityService service;

    public EmployeeController(EmployeeEntityService service) {
        this.service = service;
    }
    @GetMapping()
    public List<Employee> getAll(){
        return service.getAll();
    }
    @PostMapping()
    public Employee insert(@RequestBody EmployeeRequest request){
        return service.insert(request);
    }
    @PutMapping("/{id}")
    public Employee update(@PathVariable Integer id,@RequestBody EmployeeRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
}

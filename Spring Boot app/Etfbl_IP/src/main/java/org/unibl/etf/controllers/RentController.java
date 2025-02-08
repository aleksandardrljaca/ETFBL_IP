package org.unibl.etf.controllers;

import org.springframework.web.bind.annotation.*;
import org.unibl.etf.models.dto.Rent;
import org.unibl.etf.models.dto.RentIncomePerDay;
import org.unibl.etf.models.dto.RentIncomePerVehicleType;
import org.unibl.etf.services.RentEntityService;

import java.util.List;

@RestController
@RequestMapping("/api/rents")
@CrossOrigin(origins = "*")

public class RentController {
    private final RentEntityService rentService;

    public RentController(RentEntityService rentService) {
        this.rentService = rentService;
    }

    @GetMapping()
    public List<Rent> getAll(){
        return rentService.getAll();
    }
    @GetMapping("/current-rents")
    public List<Rent> getCurrentRents(){
        return rentService.getCurrentRents();
    }
    @GetMapping("/income-month/{month}")
    public List<RentIncomePerDay> getRentsIncomePerDays(@PathVariable Integer month){
        return rentService.getRentIncomePerDay(month);
    }
    @GetMapping("/income-vehicle-type")
    public List<RentIncomePerVehicleType> getRentsIncomePerVehicleType(){
        return rentService.getRentIncomePerVehicleType();
    }
}

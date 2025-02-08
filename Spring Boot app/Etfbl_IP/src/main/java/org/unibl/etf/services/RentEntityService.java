package org.unibl.etf.services;

import org.unibl.etf.models.dto.Rent;
import org.unibl.etf.models.dto.RentIncomePerDay;
import org.unibl.etf.models.dto.RentIncomePerVehicleType;

import java.util.List;

public interface RentEntityService {
    List<Rent> getAll();
    List<Rent> getCurrentRents();
    List<RentIncomePerDay> getRentIncomePerDay(Integer month);
    List<RentIncomePerVehicleType> getRentIncomePerVehicleType();
}

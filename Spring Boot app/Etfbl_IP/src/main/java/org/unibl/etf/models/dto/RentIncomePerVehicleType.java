package org.unibl.etf.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.unibl.etf.models.enums.VehicleType;

@Data
@AllArgsConstructor
public class RentIncomePerVehicleType {
    private VehicleType vehicleType;
    private Double total;
}

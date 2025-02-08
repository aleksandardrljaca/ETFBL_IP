package org.unibl.etf.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentIncomePerDay {
    private Integer day;
    private Double total;
}

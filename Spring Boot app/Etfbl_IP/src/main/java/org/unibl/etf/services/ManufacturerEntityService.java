package org.unibl.etf.services;

import org.unibl.etf.models.dto.Manufacturer;
import org.unibl.etf.models.dto.ManufacturerRequest;

import java.util.List;

public interface ManufacturerEntityService {
    List<Manufacturer> getAll();
    Manufacturer insert(ManufacturerRequest request);
    Manufacturer update(Integer id,ManufacturerRequest request);
    void delete(Integer id);
}

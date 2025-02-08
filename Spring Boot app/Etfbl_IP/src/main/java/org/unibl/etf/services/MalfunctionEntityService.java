package org.unibl.etf.services;

import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Malfunction;
import org.unibl.etf.models.dto.MalfunctionRequest;
import org.unibl.etf.models.dto.MalfunctionsPerVehicle;

import java.util.List;

public interface MalfunctionEntityService {
    Malfunction insert(MalfunctionRequest request) throws NotFoundException;
    List<Malfunction> getAll();
    List<MalfunctionsPerVehicle> getNumberOfMalfunctionsPerVehicle();
    void delete(Integer id);
}

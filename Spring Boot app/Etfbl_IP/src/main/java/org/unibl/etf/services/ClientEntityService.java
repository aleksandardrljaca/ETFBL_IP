package org.unibl.etf.services;

import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.BlockClientRequest;
import org.unibl.etf.models.dto.Client;

import java.util.List;

public interface ClientEntityService {
    List<Client> getAll();
    boolean block(Integer id, BlockClientRequest request) throws NotFoundException;
}

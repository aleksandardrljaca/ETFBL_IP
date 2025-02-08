package org.unibl.etf.services;

import org.unibl.etf.exceptions.NotFoundException;
import org.unibl.etf.models.dto.Promotion;

import java.util.List;

public interface PromotionEntityService {
    List<Promotion> getAll();
    Promotion getById(Integer id) throws NotFoundException;
}

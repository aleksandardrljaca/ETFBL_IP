package org.unibl.etf.services;

import org.unibl.etf.models.dto.Pricing;
import org.unibl.etf.models.dto.PricingRequest;

import java.util.List;

public interface PricingEntityService {
    Pricing insert(PricingRequest request);
    Pricing update(Integer id, PricingRequest request);
    List<Pricing> getAll();
    void delete(Integer id);
}

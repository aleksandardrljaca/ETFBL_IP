package org.unibl.etf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unibl.etf.models.entities.ManufacturerEntity;

import java.util.Optional;

@Repository
public interface ManufacturerEntityRepository extends JpaRepository<ManufacturerEntity,Integer> {
    Optional<ManufacturerEntity> findByName(String name);
}

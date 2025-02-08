package org.unibl.etf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.unibl.etf.models.entities.EmployeeEntity;

import java.util.Optional;

@Repository
public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity,Integer> {

    Optional<EmployeeEntity> findEmployeeEntitiesByUsername(String username);
}

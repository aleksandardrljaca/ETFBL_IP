package org.unibl.etf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.unibl.etf.models.dto.MalfunctionsPerVehicle;
import org.unibl.etf.models.entities.MalfunctionEntity;

import java.util.List;

@Repository
public interface MalfunctionEntityRepository extends JpaRepository<MalfunctionEntity,Integer> {
    @Query("select m.vehicleByVehicleId.id as vehicleId,count(*) as num from MalfunctionEntity m group by vehicleId order by num")
    List<Object[]> getNumberOfMalfunctionsPerVehicle();
}

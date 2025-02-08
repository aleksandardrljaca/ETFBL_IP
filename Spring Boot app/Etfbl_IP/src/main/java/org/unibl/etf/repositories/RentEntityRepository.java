package org.unibl.etf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.unibl.etf.models.dto.RentIncomePerDay;
import org.unibl.etf.models.dto.RentIncomePerVehicleType;
import org.unibl.etf.models.entities.RentEntity;

import java.sql.Time;
import java.util.List;

@Repository
public interface RentEntityRepository extends JpaRepository<RentEntity,Integer> {
    @Query("select r from RentEntity r where r.date=current date and r.endTime>current time ")
    List<RentEntity> findAllByEndTimeAfterCurrentTime();

    @Query("select day(r.date) as day,sum(r.price) as total from RentEntity r where month(r.date)=:month group by day(r.date) order by day(r.date)")
    List<Object[]> rentIncomePerDay(Integer month);

    @Query("select v.vehicleType,SUM(r.price) from VehicleEntity v inner join RentEntity r on r.vehicleByVehicleId.id=v.id group by v.vehicleType ")
    List<Object[]> rentIncomePerVehicleTypes();


}
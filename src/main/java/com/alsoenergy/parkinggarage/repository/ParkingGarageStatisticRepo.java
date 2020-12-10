package com.alsoenergy.parkinggarage.repository;

import com.alsoenergy.parkinggarage.entity.ParkingGarageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingGarageStatisticRepo extends JpaRepository<ParkingGarageEntity, Long> {
    Optional<ParkingGarageEntity> findParkingGarageEntityBySpotIdAndVehiclePlateNumber(String spotId, String vehPlateNumber);
}

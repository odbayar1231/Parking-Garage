package com.alsoenergy.parkinggarage.service.impl;

import com.alsoenergy.parkinggarage.constant.CommonConstants;
import com.alsoenergy.parkinggarage.entity.ParkingGarageEntity;
import com.alsoenergy.parkinggarage.model.Level;
import com.alsoenergy.parkinggarage.model.Spot;
import com.alsoenergy.parkinggarage.model.SpotSize;
import com.alsoenergy.parkinggarage.model.Vehicle;
import com.alsoenergy.parkinggarage.repository.LevelRepository;
import com.alsoenergy.parkinggarage.repository.ParkingGarageStatisticRepo;
import com.alsoenergy.parkinggarage.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private LevelRepository levelRepository;

    @Override
    public boolean parkInVehicle(Vehicle vehicle) {

        List<Level> levels = levelRepository.getLevels();
//O(n * m) levelLenght * spotLength
        for (Level level : levels) {
            List<Spot> spots = level.getSpots();
            for (int i = 0; i < spots.size(); i++) {
                Spot spot = spots.get(i);
                if (spot.getVehicle() == null) {

                    if (!vehicle.getVehicleType().equals(CommonConstants.BUS)
                            && vehicle.isFitInSpot(spot)) {                 //for motorcycle and car
                        spot.setVehicle(vehicle);
                        createDBRecord(spot, vehicle);
                        return true;
                    } else if (vehicle.getVehicleType().equals(CommonConstants.BUS)) { //for Bus
                        //SpotSize spotSize = spot.getSpotSize();
                        int counter = 0;
                        //int startIndex = i;
                        for (int j = i; j < i + CommonConstants.BUS_SPOT_LENGTH && j < spots.size(); j++) {
                            if (spots.get(j).getVehicle() == null
                                    && spots.get(j).getSpotSize().equals(SpotSize.LARGE)
                                    && spots.get(j).getSpotId().charAt(0) == spots.get(i).getSpotId().charAt(0)) {
                                counter++;
                            }
                        }
                        if (counter == CommonConstants.BUS_SPOT_LENGTH) {
                            //spot.setVehicle(vehicle);
                            for (int k = i; k < i + CommonConstants.BUS_SPOT_LENGTH && k < spots.size(); k++) {
                                spots.set(k, Spot.builder().spotId(spots.get(k).getSpotId())
                                        .vehicle(vehicle).spotSize(SpotSize.LARGE).build());
                                createDBRecord(spots.get(k), vehicle);
                            }
                            return true;
                        }
                    }
                }

                // end yu ch bhgu tul for davtalt urgeljilne

            }
        }
        System.out.println(" No Space for the vehicle: " + vehicle);
        return false;
    }

    @Autowired
    private ParkingGarageStatisticRepo parkingGarageStatisticRepo;

    private void createDBRecord(Spot spot, Vehicle vehicle) {
        ParkingGarageEntity parkingGarageEntity = new ParkingGarageEntity();
        parkingGarageEntity.setSpotId(String.valueOf(spot.getSpotId()));
        parkingGarageEntity.setVehiclePlateNumber(vehicle.getPlateNumber());
        parkingGarageEntity.setStartParking(new Timestamp(System.currentTimeMillis()));
        parkingGarageStatisticRepo.save(parkingGarageEntity);
    }

    private void updateDBRecord(Spot spot, Vehicle vehicle) {
        Optional<ParkingGarageEntity> parkingGarageEntityOptional =
                parkingGarageStatisticRepo.findParkingGarageEntityBySpotIdAndVehiclePlateNumber(spot.getSpotId(),
                vehicle.getPlateNumber());
        //System.out.println("parkingGarageEntityOptional = " + parkingGarageEntityOptional);
        ParkingGarageEntity parkingGarageEntity;
        if(parkingGarageEntityOptional.isPresent()) {
            parkingGarageEntity = parkingGarageEntityOptional.get();
            parkingGarageEntity.setStopParking(new Timestamp(System.currentTimeMillis()));
            parkingGarageStatisticRepo.save(parkingGarageEntity);
            //System.out.println("DB persisted");
        } else {
            //System.out.println("DB is not persisted~!");
        }

    }


    @Override
    public boolean parkOutVehicle(Vehicle vehicle) {

        List<Level> levels = levelRepository.getLevels();

        try {
            for (Level level : levels) {
                int i = 0;
                do {
                   Optional<Spot> sp = level.getSpots().stream()
                            .filter(spot -> spot.getVehicle() != null
                                    && spot.getVehicle().getPlateNumber().equalsIgnoreCase(vehicle.getPlateNumber()))
                            .findAny();
                   if(sp.isPresent()) {
//                       System.out.println("sp.get() = " + sp.get());
//                       System.out.println("vehicle = " + vehicle);
                       updateDBRecord(sp.get(), vehicle);
                       sp.get().setVehicle(null);
                   }
                    i++;
                } while (vehicle.getVehicleType().equals(CommonConstants.BUS)
                        && i < CommonConstants.BUS_SPOT_LENGTH);
            }
            return true;
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return false;
    }

}

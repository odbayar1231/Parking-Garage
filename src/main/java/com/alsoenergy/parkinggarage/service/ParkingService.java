package com.alsoenergy.parkinggarage.service;

import com.alsoenergy.parkinggarage.model.Spot;
import com.alsoenergy.parkinggarage.model.Vehicle;

import java.util.List;

public interface ParkingService {

    boolean parkInVehicle(Vehicle vehicle);
    boolean parkOutVehicle(Vehicle vehicle);

}

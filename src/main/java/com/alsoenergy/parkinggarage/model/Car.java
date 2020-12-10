package com.alsoenergy.parkinggarage.model;

import com.alsoenergy.parkinggarage.constant.CommonConstants;

public class Car extends Vehicle {

public Car(String plateNumber) {
    setPlateNumber(plateNumber);
    setVehicleType(CommonConstants.CAR);
    setNeededSpot(1);
}
    @Override
    public boolean isFitInSpot(Spot spot) {
        //  return spot.getSize() == VehicleSize.CarSize || spot.getSize() == VehicleSize.Motorcycle;
        // String spotSize = spot.getSpotSize().toString(); //motorcycle, compact, large;
        return !spot.getSpotSize().equals(SpotSize.MOTORCYCLE);
    }
}

package com.alsoenergy.parkinggarage.model;

import com.alsoenergy.parkinggarage.constant.CommonConstants;

public class Bus extends Vehicle {
    public Bus (String plateNumber) {
        setPlateNumber(plateNumber);
        setVehicleType(CommonConstants.BUS);
        setNeededSpot(5);
    }
    @Override
    public boolean isFitInSpot(Spot spot) { // 5 Large spots needed for bus!
        return spot.getSpotSize().equals(SpotSize.LARGE);
    }
}

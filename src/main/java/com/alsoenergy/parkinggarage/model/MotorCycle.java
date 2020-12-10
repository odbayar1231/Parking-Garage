package com.alsoenergy.parkinggarage.model;

import com.alsoenergy.parkinggarage.constant.CommonConstants;

public class MotorCycle extends Vehicle {

    public MotorCycle(String plateNumber) {
        setPlateNumber(plateNumber);
        setVehicleType(CommonConstants.MOTORCYCLE);
        setNeededSpot(1);
    }
    @Override
    public boolean isFitInSpot(Spot spot) {
        return true;
    }
}

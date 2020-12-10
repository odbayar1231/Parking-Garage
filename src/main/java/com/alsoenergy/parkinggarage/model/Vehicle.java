package com.alsoenergy.parkinggarage.model;

import lombok.Data;

import java.util.List;

@Data
public abstract class Vehicle {
    private String plateNumber;
    private String vehicleType;
    private Integer neededSpot;

    public abstract boolean isFitInSpot(Spot spot);
}

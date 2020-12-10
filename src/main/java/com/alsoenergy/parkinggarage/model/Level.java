package com.alsoenergy.parkinggarage.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data@Builder
public class Level {
    private Integer floorNumber;
    private List<Spot> spots;
}
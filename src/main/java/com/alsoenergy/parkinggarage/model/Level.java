package com.alsoenergy.parkinggarage.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data@Builder
public class Level {
    private Integer row;
    private Integer column;
    private Integer floorNumber;
    private List<Spot> spots;
}
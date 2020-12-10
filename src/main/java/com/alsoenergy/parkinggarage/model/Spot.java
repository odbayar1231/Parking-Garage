package com.alsoenergy.parkinggarage.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@Builder
public class Spot {
    private String spotId;
    private Vehicle vehicle;
    private SpotSize spotSize; //   motorcycle spots, compact spots, and large spots;
}

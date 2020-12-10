package com.alsoenergy.parkinggarage.service.impl;

import com.alsoenergy.parkinggarage.entity.ParkingGarageEntity;
import com.alsoenergy.parkinggarage.model.Level;
import com.alsoenergy.parkinggarage.model.Spot;
import com.alsoenergy.parkinggarage.model.SpotSize;
import com.alsoenergy.parkinggarage.repository.LevelRepository;
import com.alsoenergy.parkinggarage.service.ParkingGarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
@Service
public class ParkingGarageServiceImpl implements ParkingGarageService {

    @Autowired
    private LevelRepository levelRepository;

    @Override
    public void createParkingGarage(int floorNumbers, char[][] level2DPlan) {

        //2D array Floor plan//
        // int[row][col]
        /*
        m=motorcycle
        c=compact
        l=large
        8x8
          0 1 2 3 4 5 6 7
        0 c c c c c c m m
        1 l l l l l l l l
         */
        /*
        char[][] level2DPlan = new char[][] {
                {'c', 'c', 'c', 'c', 'c', 'c', 'm', 'm'},
                {'l', 'l', 'l', 'l', 'l', 'l', 'l', 'l',}
        };
        */

        List<Spot> spots = new LinkedList<>();
        Spot spot;
        for(int i = 0; i < level2DPlan.length; i++) {
            for (int j = 0; j < level2DPlan[0].length; j++) {
                char currentChar = level2DPlan[i][j];
                if (currentChar == 'c') {
                    spot = Spot.builder()
                            .spotSize(SpotSize.COMPACT)
                            .spotId(String.valueOf(i) + String.valueOf(j)).build();
                } else if(currentChar == 'm') {
                    spot = Spot.builder()
                            .spotSize(SpotSize.MOTORCYCLE)
                            .spotId(String.valueOf(i) + String.valueOf(j))
                            .build();
                } else {
                    spot = Spot.builder()
                            .spotId(String.valueOf(i) + String.valueOf(j))
                            .spotSize(SpotSize.LARGE)
                            .build();
                }
                spots.add(spot);
            }
        }
        List<Level> levels = new LinkedList<>();
        for(int l = 0; l < floorNumbers; l++) {
                Level nLevel =Level.builder()
                        .floorNumber(l)
                        .spots(cloneSpots(spots))
                        .build();
                levels.add(nLevel);
        }
        levelRepository.setLevels(levels);
    }

    private List<Spot> cloneSpots(List<Spot> spots) {
        List<Spot> clonedSpots = new LinkedList<>();
        spots.forEach(spot -> {
            clonedSpots.add(cloneSpot(spot));
        });
        return clonedSpots;
    }

    private Spot cloneSpot(Spot spot) { //deep clone
        return Spot.builder()
                .spotId(spot.getSpotId())
                .spotSize(spot.getSpotSize())
                .vehicle(spot.getVehicle())
        .build();
    }
}

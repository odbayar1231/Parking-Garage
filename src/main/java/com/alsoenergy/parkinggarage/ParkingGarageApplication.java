package com.alsoenergy.parkinggarage;

import com.alsoenergy.parkinggarage.model.*;
import com.alsoenergy.parkinggarage.repository.LevelRepository;
import com.alsoenergy.parkinggarage.service.ParkingGarageService;
import com.alsoenergy.parkinggarage.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ParkingGarageApplication implements CommandLineRunner {
    
    @Autowired
    private ParkingGarageService parkingGarageService;

    @Autowired
    private ParkingService parkingService;
    
    @Autowired
    private LevelRepository levelRepository;

    public static void main(String[] args) {
        SpringApplication.run(ParkingGarageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
          char[][] level2DPlan = new char[][] {
                {'c', 'l', 'l', 'l', 'l'},
                {'l', 'l', 'l', 'l', 'l',}
        };
        parkingGarageService.createParkingGarage(2, level2DPlan);

//        printLevels();

        Vehicle motorCycle1 = new MotorCycle("MOT111");
        Vehicle bus1 = new Bus("BUS111");
        Vehicle bus2 = new Bus("BUS222");
        Vehicle bus3 = new Bus("BUS333");

        System.out.println("\n------------------------------------------------------");
        System.out.println("Parking in: 1 motorcycle and 3 buses.");
        System.out.println("\n------------------------------------------------------");
        parkingService.parkInVehicle(motorCycle1);
        parkingService.parkInVehicle(bus1);
        parkingService.parkInVehicle(bus2);
        parkingService.parkInVehicle(bus3);
        //printLevels();
        printLevels2();

        parkingService.parkOutVehicle(motorCycle1);
        parkingService.parkOutVehicle(bus2);
        //parkingService.parkOutVehicle(null);
        System.out.println("\n------------------------------------------------------");
        System.out.println("Parking out: 1 motorcycle and bus2.");
        //printLevels();
        printLevels2();

    }

    private void printLevels() {
        System.out.println("\n------------------------------------------------------");
        levelRepository.getLevels().forEach(level -> {
            System.out.println("level = " + level);
        });
        System.out.println("");
    }

    private void printLevels2(){
        System.out.println("\n------------------------------------------------------");
        List<Level> levels = levelRepository.getLevels();

        for(Level level : levels){
            System.out.println("level : " + level.getFloorNumber());
            List<Spot> spots = level.getSpots();
            for(int j = 0; j < spots.size(); j++){
                System.out.print(spots.get(j));
                //System.out.println(level.getColumn());
                if(spots.get(j).getSpotId().charAt(1) ==  level.getColumn()-1 + '0'){
                    System.out.println();
                }
            }

        }
    }

}

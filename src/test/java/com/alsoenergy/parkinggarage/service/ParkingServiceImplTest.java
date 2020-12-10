package com.alsoenergy.parkinggarage.service;

import com.alsoenergy.parkinggarage.ParkingGarageApplication;
import com.alsoenergy.parkinggarage.model.Bus;
import com.alsoenergy.parkinggarage.model.MotorCycle;
import com.alsoenergy.parkinggarage.model.Vehicle;
import com.alsoenergy.parkinggarage.repository.LevelRepository;
import com.alsoenergy.parkinggarage.service.impl.ParkingGarageServiceImpl;
import com.alsoenergy.parkinggarage.service.impl.ParkingServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

public class ParkingServiceImplTest {


    @Autowired
    private ParkingGarageService parkingGarageService;

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private LevelRepository levelRepository;




    private void printLevels() {
        System.out.println("\n------------------------------------------------------");
        levelRepository.getLevels().forEach(level -> {
            System.out.println("level = " + level);
        });
    }




    @Test
    public void parkInVehicle() {
        parkingGarageService = new ParkingGarageServiceImpl();
        parkingService = new ParkingServiceImpl();
        levelRepository= new LevelRepository();

        char[][] level2DPlan = new char[][] {
                {'c', 'c', 'c', 'm', 'm'},
                {'l', 'l', 'l', 'l', 'l',}
        };
        parkingGarageService.createParkingGarage(2, level2DPlan);

//        printLevels();

        Vehicle motorCycle1 = new MotorCycle("MOT111");
        Vehicle bus1 = new Bus("BUS111");
        Vehicle bus2 = new Bus("BUS222");
        Vehicle bus3 = new Bus("BUS333");
        parkingService.parkInVehicle(motorCycle1);
        parkingService.parkInVehicle(bus1);
        parkingService.parkInVehicle(bus2);
        parkingService.parkInVehicle(bus3);
        printLevels();

        parkingService.parkOutVehicle(motorCycle1);
        parkingService.parkOutVehicle(bus2);



        printLevels();

    }
}

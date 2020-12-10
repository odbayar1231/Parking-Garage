package com.alsoenergy.parkinggarage.repository;

import com.alsoenergy.parkinggarage.entity.ParkingGarageEntity;
import com.alsoenergy.parkinggarage.model.Level;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository@Data
public class LevelRepository {
    private List<Level> levels = new LinkedList<>();
}

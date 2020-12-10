package com.alsoenergy.parkinggarage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDate;


@NoArgsConstructor@Data@Entity
public class ParkingGarageEntity {

    @Id@GeneratedValue
    private Long parkingGarageEntityId;

    private String spotId; //fl0sp0
    private String vehiclePlateNumber;
    private Timestamp startParking;
    private Timestamp stopParking;

}

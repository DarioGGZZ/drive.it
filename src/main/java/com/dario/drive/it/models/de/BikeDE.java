package com.dario.drive.it.models.de;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "BIKES")
public class BikeDE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "AVAILABLE")
    private boolean available;

    @Column(name = "TRAVELED_HOURS")
    private double traveledHours;

    @Column(name = "USEFUL_LIFE")
    private double usefulLife;

    /*@ManyToOne()
    @JoinColumn(name = "STATIONS_ID")
    private StationDE station;*/

}

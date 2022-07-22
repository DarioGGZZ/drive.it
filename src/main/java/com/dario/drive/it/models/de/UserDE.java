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
@Table(name = "USERS")
public class UserDE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "DNI")
    private Long dni;

    @Column(name = "USING_BIKE")
    private boolean usingBike;

    @Column(name = "BIKE")
    private Long bikeId;

    @Column(name = "TOTAL_HOURS")
    private double totalHours;

    @Column(name = "STATUS")
    private boolean status;

}

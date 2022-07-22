package com.dario.drive.it.controllers.to;

import com.dario.drive.it.enums.BikeAvailabilityEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BikeTO {

    private Long id;
    private BikeAvailabilityEnum status;
    private double travelHours;
    private double usefulLife;
    /*private StationTO station;*/
}

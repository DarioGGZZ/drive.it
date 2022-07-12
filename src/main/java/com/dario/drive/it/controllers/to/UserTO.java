package com.dario.drive.it.controllers.to;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserTO {

    private Long id;

    @Size(min = 1, max = 100)
    @NotNull(message = "Name value is required")
    private String name;

    @Size(min = 1, max = 100)
    @NotNull(message = "Surname value is required")
    private String surname;

    @NotNull(message = "Dni value is required")
    private Long dni;

    private boolean usingBike;

    private double totalHours;

    private String status;
}

package com.dario.drive.it.controllers.to;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class StationTO {

    private Long id;

    @Size(min = 1, max = 100)
    @NotNull(message = "Name value is required")
    private String name;

    @Size(min = 1, max = 200)
    @NotNull(message = "Address value is required")
    private String address;

    private int availableBikes;

    @Min(7)
    @Max(15)
    @NotNull(message = "Total slots value is required")
    private int totalSlots;

    private List<BikeTO> bikes;
}

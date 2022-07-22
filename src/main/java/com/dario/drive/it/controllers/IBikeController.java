package com.dario.drive.it.controllers;

import com.dario.drive.it.controllers.to.BikeTO;
import com.dario.drive.it.controllers.to.ResponseTO;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/bikes")
public interface IBikeController {

    String APPLICATION_JSON = "application/json";

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Creates a new bike")
    @PostMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> addBike();

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Obtain bike info")
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON)
    ResponseEntity<?> getBikeById(@ApiParam(value = "Id of the bike to get") @PathVariable("id") Long id);

}

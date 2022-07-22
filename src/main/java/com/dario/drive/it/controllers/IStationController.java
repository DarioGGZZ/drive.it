package com.dario.drive.it.controllers;

import com.dario.drive.it.controllers.to.ResponseTO;
import com.dario.drive.it.controllers.to.StationTO;
import com.dario.drive.it.controllers.to.UserTO;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/stations")
public interface IStationController {

    String APPLICATION_JSON = "application/json";

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Creates a new station")
    @PostMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> addStation(@ApiParam(value = "Station to create" ,required=true )@Valid @RequestBody StationTO station);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Obtain station info")
    @GetMapping(value = "/{name}", produces = APPLICATION_JSON)
    ResponseEntity<?> getStationByName(@ApiParam(value = "Name of the station to get") @PathVariable("name") String name);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Obtain bikes in station")
    @GetMapping(value = "/{name}/bikes", produces = APPLICATION_JSON)
    ResponseEntity<?> getBikesFromStation(@ApiParam(value = "Bikes id of the station") @PathVariable("name") String name);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Obtain station info")
    @GetMapping(value = "/{name}/bike/{bike-id}/for/{user-dni}", produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> getBikeFromStationToUser(
            @ApiParam(value = "Name of the station to get") @PathVariable("name") String name,
            @ApiParam(value = "Bike id") @PathVariable("bike-id") Long id,
            @ApiParam(value = "User dni") @PathVariable("user-dni") Long dni);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Obtain station info")
    @GetMapping(value = "/{name}/user/{user-dni}/returns/bike/after/{total-hours}", produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> returnBikeFromUserToStation(
            @ApiParam(value = "Name of the station to get") @PathVariable("name") String name,
            @ApiParam(value = "User dni") @PathVariable("user-dni") Long dni,
            @ApiParam(value = "Time used") @PathVariable("total-hours") double totalHours);
}

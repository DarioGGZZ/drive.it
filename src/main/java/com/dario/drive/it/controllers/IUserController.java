package com.dario.drive.it.controllers;

import com.dario.drive.it.controllers.to.ResponseTO;
import com.dario.drive.it.controllers.to.UserTO;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/users")
public interface IUserController {

    String APPLICATION_JSON = "application/json";

    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Creates a new user")
    @PostMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> createUser(@ApiParam(value = "User to create" ,required=true )@Valid @RequestBody UserTO user);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Obtain the user associated with the dni")
    @GetMapping(value = "/{dni}", produces = APPLICATION_JSON)
    ResponseEntity<?> getUserByUserDni(@ApiParam(value = "Dni of the user to get") @PathVariable("dni") Long userDni);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Activates the dni's associated user")
    @PutMapping(value = "/{dni}/activate",produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> activateUser(@ApiParam(value = "Dni of the user to activate") @PathVariable("dni") Long userDni);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Deactivates the dni's associated user")
    @PutMapping(value = "/{dni}/deactivate", produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> deactivateUser(@ApiParam(value = "Dni of the user to deactivate") @PathVariable("dni") Long userDni);
}

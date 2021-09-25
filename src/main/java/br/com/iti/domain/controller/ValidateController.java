package br.com.iti.domain.controller;

import br.com.iti.domain.model.ResponseDTO;
import br.com.iti.domain.service.ValidatePasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(value = "/api/v1")
public class ValidateController {

    private ValidatePasswordService validatePassword;

    @Autowired
    ValidateController(ValidatePasswordService validatePassword){
        this.validatePassword = validatePassword;
    }

    @Operation(summary = "Check String password is valid.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password validated successfully!",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Required request header 'password' for method parameter type String is not present",
                    content = @Content)})
    @PostMapping(value = "valid", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO valid(@RequestHeader String password){
        return validatePassword.validate(password);
    }

}
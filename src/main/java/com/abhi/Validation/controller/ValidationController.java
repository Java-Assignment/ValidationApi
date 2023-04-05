package com.abhi.Validation.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/validation",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
@Validated
@Tag(name = "vaidation api",description = "it calls the formula and rule apis")
public interface ValidationController {

    @GetMapping("/{fileNumber}")
    @Operation(summary = "Get information about the given FileNumber")
    ResponseEntity<String>validate(@PathVariable("fileNumber") @NotNull String fileNumber);

}

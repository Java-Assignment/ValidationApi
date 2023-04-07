package com.abhi.Validation.controller;

import com.abhi.Validation.service.ValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ValidationControllerImpl implements ValidationController{
    private ValidationService validationService;
    public ValidationControllerImpl(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public ResponseEntity<String> validate(String fileNumber) {
        String  validated=validationService.CheckValidation(fileNumber);
        return new ResponseEntity<>(validated, HttpStatus.OK);
    }
}

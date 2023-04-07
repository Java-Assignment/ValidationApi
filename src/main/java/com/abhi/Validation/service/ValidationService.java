package com.abhi.Validation.service;

import com.abhi.Validation.exception.InvalidFileNumberException;

public interface ValidationService {


    String CheckValidation(String fileNumber) throws InvalidFileNumberException;
}

package com.abhi.Validation.externalsvc.formuladatasvc;


import lombok.Data;

@Data
public class FileDataDTO {
    private String fileNumber;
    private String version;

    private String alphabet;

    private int count;
}

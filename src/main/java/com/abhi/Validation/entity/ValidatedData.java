package com.abhi.Validation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Validation")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ValidatedData {
    @Id
    private String fileNumber;
    private String Variable1;
    private int Value1;
    private String Variable2;
    private int Value2;
    private String Variable3;
    private int Value3;
    private int Perimeter;
}

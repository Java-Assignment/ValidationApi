package com.abhi.Validation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Validation")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class File {
    @Id
    private String fileNumber;
    private String alphabet;
    private int count;
}

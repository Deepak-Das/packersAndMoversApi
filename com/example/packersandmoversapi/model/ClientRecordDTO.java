package com.example.packersandmoversapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClientRecordDTO {
    private Long recordId;
    private String phoneNo;
    private String fullName;
    private String message;
    private String date;

    // Constructors, getters, and setters
}

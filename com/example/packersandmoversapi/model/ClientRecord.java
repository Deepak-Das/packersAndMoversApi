package com.example.packersandmoversapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class ClientRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recordId;

    private String phoneNo;
    private String fullName;
    private String message;
    private Date date;
}

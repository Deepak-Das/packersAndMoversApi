package com.example.packersandmoversapi.service;

import com.example.packersandmoversapi.model.ApiResponse;
import com.example.packersandmoversapi.model.ClientRecordDTO;

import java.sql.Date;
import java.util.List;

public interface ClientRecordService {
    ClientRecordDTO getRecordById(Long recordId);
    List<ClientRecordDTO> getAllRecords();
    ClientRecordDTO createRecord(ClientRecordDTO recordDTO);
    ClientRecordDTO updateRecord(Long recordId, ClientRecordDTO recordDTO);
    ApiResponse deleteRecord(Long recordId);
    List<ClientRecordDTO> getRecordsByDate(Date date);
}

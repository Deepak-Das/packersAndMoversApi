package com.example.packersandmoversapi.controller;

import com.example.packersandmoversapi.model.ApiResponse;
import com.example.packersandmoversapi.model.ClientRecordDTO;
import com.example.packersandmoversapi.service.ClientRecordService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/records")
@SecurityRequirement(name = "JwtAuth")

public class RecordController {
    private final ClientRecordService recordService;

    public RecordController(ClientRecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/{recordId}")
    public ClientRecordDTO getRecordById(@PathVariable Long recordId) {
        return recordService.getRecordById(recordId);
    }

    @GetMapping
    public List<ClientRecordDTO> getAllRecords() {
        return recordService.getAllRecords();
    }

    @PostMapping
    public ClientRecordDTO createRecord(@RequestBody ClientRecordDTO recordDTO) {
        return recordService.createRecord(recordDTO);
    }

    @PutMapping("/{recordId}")
    public ClientRecordDTO updateRecord(@PathVariable Long recordId, @RequestBody ClientRecordDTO recordDTO) {
        return recordService.updateRecord(recordId, recordDTO);
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long recordId) {
        ApiResponse apiResponse = recordService.deleteRecord(recordId);
        return new ResponseEntity<>(apiResponse.getMessage(), HttpStatus.OK);
    }
}


package com.example.packersandmoversapi.service;

import com.example.packersandmoversapi.exception.ResourceNotFoundException;
import com.example.packersandmoversapi.model.ApiResponse;
import com.example.packersandmoversapi.model.ClientRecordDTO;
import com.example.packersandmoversapi.model.ClientRecord;
import com.example.packersandmoversapi.repository.ClientRecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientRecordServiceImpl implements ClientRecordService {
    private final ClientRecordRepository recordRepository;
    private final ModelMapper modelMapper;

    public ClientRecordServiceImpl(ClientRecordRepository recordRepository, ModelMapper modelMapper) {
        this.recordRepository = recordRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClientRecordDTO getRecordById(Long recordId) {
        ClientRecord record = recordRepository.findById(recordId).orElseThrow(() -> new ResourceNotFoundException("record", "recordId", recordId));
        return modelMapper.map(record, ClientRecordDTO.class);
    }

    @Override
    public List<ClientRecordDTO> getAllRecords() {
        List<ClientRecord> records = recordRepository.findAll();
        return records.stream().map(clientRecord -> modelMapper.map(clientRecord,ClientRecordDTO.class) ).collect(Collectors.toList());
    }

    @Override
    public ClientRecordDTO createRecord(ClientRecordDTO recordDTO) {
        ClientRecord record = modelMapper.map(recordDTO,ClientRecord.class);
        ClientRecord savedRecord = recordRepository.save(record);
        return modelMapper.map(savedRecord,ClientRecordDTO.class);
    }

    @Override
    public ClientRecordDTO updateRecord(Long recordId, ClientRecordDTO recordDTO) {
        ClientRecord record = recordRepository.findById(recordId).orElseThrow(() -> new ResourceNotFoundException("record", "recordId", recordId));


            record.setFullName(recordDTO.getFullName());
            record.setMessage(recordDTO.getMessage());
            record.setPhoneNo(recordDTO.getPhoneNo());
            record.setDate(Date.valueOf(recordDTO.getDate()));
            ClientRecord updatedRecord = recordRepository.save(record);
            return modelMapper.map(updatedRecord,ClientRecordDTO.class);

    }

    @Override
    public ApiResponse deleteRecord(Long recordId) {
        recordRepository.deleteById(recordId);
        ApiResponse apiResponse = new ApiResponse();
        return ApiResponse.builder().message("Deleted Successfully").error_status("NO ERROR").build();

    }

    @Override
    public List<ClientRecordDTO> getRecordsByDate(Date date) {
        List<ClientRecord> records = recordRepository.findAllByDate(date);
        return records.stream().map(record -> modelMapper.map(record,ClientRecordDTO.class)).collect(Collectors.toList());
    }


}

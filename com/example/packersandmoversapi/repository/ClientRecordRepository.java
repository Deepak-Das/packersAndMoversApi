package com.example.packersandmoversapi.repository;

import com.example.packersandmoversapi.model.ClientRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ClientRecordRepository extends JpaRepository<ClientRecord, Long> {
    // Add custom queries if needed
    List<ClientRecord> findAllByDate(Date date);
}

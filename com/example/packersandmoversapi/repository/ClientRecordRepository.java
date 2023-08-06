package com.example.packersandmoversapi.repository;

import com.example.packersandmoversapi.model.ClientRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRecordRepository extends JpaRepository<ClientRecord, Long> {
    // Add custom queries if needed
}

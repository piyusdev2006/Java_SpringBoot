package com.piyus.SpringTransaction.repository;

import com.piyus.SpringTransaction.model.TransferRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<TransferRecord, Long> {
}

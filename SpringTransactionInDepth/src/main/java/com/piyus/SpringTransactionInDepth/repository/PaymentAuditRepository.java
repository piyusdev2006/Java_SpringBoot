package com.piyus.SpringTransactionInDepth.repository;

import com.piyus.SpringTransactionInDepth.model.PaymentAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentAuditRepository extends JpaRepository<PaymentAudit, Long> {
}

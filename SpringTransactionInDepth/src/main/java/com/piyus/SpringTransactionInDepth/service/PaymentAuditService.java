package com.piyus.SpringTransactionInDepth.service;

import com.piyus.SpringTransactionInDepth.model.Order;
import com.piyus.SpringTransactionInDepth.model.PaymentAudit;
import com.piyus.SpringTransactionInDepth.repository.PaymentAuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentAuditService {

    private PaymentAuditRepository paymentAuditRepository;

    public PaymentAuditService(
            PaymentAuditRepository paymentAuditRepository) {
        this.paymentAuditRepository = paymentAuditRepository;
    }

    @Transactional(
            propagation = Propagation.MANDATORY,
            isolation = Isolation.REPEATABLE_READ
    )
    public void audit(Order order) {
        PaymentAudit paymentAudit = new PaymentAudit(
                order.getAmount(), order.getId(),true
        );

        paymentAuditRepository.save(paymentAudit);


        //throw new RuntimeException("Something went wrong");

    }

}

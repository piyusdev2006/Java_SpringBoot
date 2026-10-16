package com.piyus.SpringTransactionInDepth.service;

import com.piyus.SpringTransactionInDepth.model.Order;
import com.piyus.SpringTransactionInDepth.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private OrderRepository  orderRepository;
    private PaymentAuditService paymentAuditService;

    public OrderService(
            OrderRepository orderRepository,
            PaymentAuditService paymentAuditService
    ) {
        this.orderRepository = orderRepository;
        this.paymentAuditService = paymentAuditService;
    }

    @Transactional
    public void placeOrder(Order order) {
        orderRepository.save(order);


        paymentAuditService.audit(order);

//        try {
//            paymentAuditService.audit(order);
//        }
//        catch (Exception e) {}

//        throw new RuntimeException("Something went wrong");
    }
}

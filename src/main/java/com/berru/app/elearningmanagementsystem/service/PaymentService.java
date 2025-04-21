package com.berru.app.elearningmanagementsystem.service;

import com.berru.app.elearningmanagementsystem.entity.Payment;
import com.berru.app.elearningmanagementsystem.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository PaymentRepository;

    public Payment addPayment(Payment payment) {
        return PaymentRepository.save(payment);
    }
}
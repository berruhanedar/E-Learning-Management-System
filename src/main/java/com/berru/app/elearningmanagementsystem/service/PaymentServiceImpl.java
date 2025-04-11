package com.berru.app.elearningmanagementsystem.service;

import com.berru.app.elearningmanagementsystem.entity.Payment;
import com.berru.app.elearningmanagementsystem.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository PaymentRepository;

    @Override
    public Payment addPayment(Payment payment) {
        return PaymentRepository.save(payment);
    }
}
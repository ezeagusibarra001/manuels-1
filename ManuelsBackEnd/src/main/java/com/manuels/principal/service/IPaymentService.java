package com.manuels.principal.service;

import com.manuels.principal.models.Payment;
import java.util.List;

public interface IPaymentService {
    
    public List<Payment> listPayments();
    
    public Payment create(Payment payment);
    
    public void delete(Payment payment);
    
    public Payment update(Payment payment);

    public Payment findWithId(Long idPayment);
    
    public List<Payment> findByName(String name); 
}
package com.manuels.principal.service;

import com.manuels.principal.dao.IPaymentDao;
import com.manuels.principal.models.Payment;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService{
    
    @Autowired
    private IPaymentDao paymentDao;
    
    @Override
    public List<Payment> listPayments() {
        return paymentDao.findAll();
    }

    @Override
    public Payment create(Payment payment) {
        return paymentDao.save(payment);
    }

    @Override
    public void delete(Payment payment) {
        paymentDao.delete(payment);
    }

    @Override
    public Payment update(Payment payment) {
        
        Payment existingPayment = paymentDao.findById(payment.getIdPayment()).orElse(null);
        
        existingPayment.setDate(LocalDate.now());
        existingPayment.setImage(payment.getImage());
        existingPayment.setName(payment.getName());
        existingPayment.setPayment(payment.getPayment());
        
        /* definir pagos etc */
        return existingPayment;
    }   

    @Override
    public Payment findWithId(Long idPayment) {
        return paymentDao.findById(idPayment).orElse(null);
    }

    @Override
    public List<Payment> findByName(String name) {
        return paymentDao.findByName(name);
    }
}
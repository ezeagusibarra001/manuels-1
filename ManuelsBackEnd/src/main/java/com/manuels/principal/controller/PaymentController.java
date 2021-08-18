package com.manuels.principal.controller;

import com.manuels.principal.exceptions.NotFoundException;
import com.manuels.principal.service.PaymentService;
import com.manuels.principal.models.Payment;
import com.manuels.principal.models.Image;
import com.manuels.principal.service.ImageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/payments")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private ImageService imageService;
    
    @PostMapping
    public ResponseEntity<Payment> create(@RequestBody Payment payment){
        
<<<<<<< HEAD
<<<<<<< HEAD
        Image img = new Image(payment.getFile().getOriginalFilename(),
                              payment.getFile().getContentType(),
                              imageService.compressBytes(payment.getFile().getBytes()));
        
        payment.setImage(img);
        
        payment.setDate(LocalDate.now());
        payment.setPayment(Boolean.FALSE);
        
=======
=======
>>>>>>> parent of 95a6d86 (Revert "Revert "payment, email, and others"")
        if(payment.getImage() != null){
            
           Image image = imageService.create(payment.getImage());
           if(image.getIdImage() != null){
               payment.setImage(image);
           }else{
               throw new Error("Error inesperado al ingresar la imagen");
           }
        }else{
            throw new Error("Tenes que ingresar el comprobante");
        }
<<<<<<< HEAD
>>>>>>> parent of f2cd3a8 (payment, email, and others)
=======
>>>>>>> parent of 95a6d86 (Revert "Revert "payment, email, and others"")
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.create(payment));
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Payment> listAll() {

        return paymentService.listPayments();
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Payment> readById(@PathVariable(value = "id") Long idPayment) throws NotFoundException{

        Payment payment = paymentService.findWithId(idPayment);

        if (payment == null) {
            throw new NotFoundException("404 | NO SE ENCUENTRA EL PAGO");
        }
        return ResponseEntity.ok(payment);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/names/{name}")
    public ResponseEntity<List<Payment>> readByName(@PathVariable(value = "name") String name) throws NotFoundException{

        List<Payment> payments = paymentService.findByName(name);

        if (payments.isEmpty()) {
            throw new NotFoundException("404 | NO SE ENCUENTRA EL PAGO");
        }
        return ResponseEntity.ok(payments);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Payment> delete(@PathVariable(value = "id") Long idPayment) throws NotFoundException{

        Payment payment = paymentService.findWithId(idPayment);

        if (payment == null) {
            throw new NotFoundException("404 | NO SE ENCUENTRA EL PAGO");
        }

        paymentService.delete(payment);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Payment> update(@RequestBody Payment payment) {

        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.update(payment));
    }
}
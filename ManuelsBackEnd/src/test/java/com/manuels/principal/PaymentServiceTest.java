package com.manuels.principal;

import com.manuels.principal.service.PaymentService;
import com.manuels.principal.dao.IPaymentDao;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    
    @MockBean
    private IPaymentDao paymentMock;
    
    @InjectMocks
    private PaymentService paymentService;
    
    public void listPaymentsTest(){
        
    }
    
    public void createPaymentTest(){
        
    }
    
    public void findWithIdTest(){
        
    }
    
    public void findByNameTest(){
        
    }
    
    public void delete(){
        
    }
}
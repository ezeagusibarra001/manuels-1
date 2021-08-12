package com.manuels.principal;

import com.manuels.principal.dao.IDateDao;
import com.manuels.principal.models.DateC;
import com.manuels.principal.service.DateService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DateServiceTest {
    
    @MockBean
    private IDateDao dateMock;
    
    @InjectMocks
    private DateService dateService;
    
    @Test
    public void listDatesTest(){
        
        DateC date1 = new DateC();
        DateC date2 = new DateC();
        
        List<DateC> dates = new ArrayList<>();
        
        Date date = new Date(System.currentTimeMillis());
        
        date1.setDate(date);
        date1.setIdDate(1L);
        
        date2.setDate(date);
        date2.setIdDate(2L);
        
        dates.add(date1);
        dates.add(date2);
        
        given(dateMock.findAll()).willReturn(dates);
        
        List<DateC> expected = dateService.listDates();
        
        assertEquals(expected, dates);
    }
    
    @Test
    public void createDateTest(){
        
        DateC date1 = new DateC();
        
        Date date = new Date(System.currentTimeMillis());
        
        date1.setDate(date);
        date1.setIdDate(1L);
        
        given(dateMock.save(date1)).willAnswer
        (invocation -> invocation.getArgument(0));
        
        DateC savedDateC = dateMock.save(date1);
        
        assertThat(savedDateC).isNotNull();
        
        verify(dateMock).save(any(DateC.class));
    }
    
    @Test
    public void findDateTest(){
        
        DateC date1 = new DateC();
        
        Date date = new Date(System.currentTimeMillis());
        
        date1.setDate(date);
        date1.setIdDate(1L);
        
        given(dateMock.findById(date1.getIdDate()))
                .willReturn(Optional.of(date1));

        verify(dateMock, never()).save(any(DateC.class));
    }
    
    @Test
    public void deleteDateTest(){
        
        DateC date1 = new DateC();
        
        Date date = new Date(System.currentTimeMillis());
        
        date1.setDate(date);
        date1.setIdDate(1L);
        
        dateService.delete(date1);
        dateService.delete(date1);
        
        verify(dateMock, times(2)).delete(date1);
    }
}
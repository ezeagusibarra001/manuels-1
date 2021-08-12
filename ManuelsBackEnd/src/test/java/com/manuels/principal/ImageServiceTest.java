package com.manuels.principal;

import com.manuels.principal.dao.IImageDao;
import com.manuels.principal.models.Image;
import com.manuels.principal.service.ImageService;
import java.awt.image.DataBufferByte;
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
public class ImageServiceTest {
    
    @MockBean
    private IImageDao imageMock;
    
    @InjectMocks
    private ImageService imageService;
    
    @Test
    public void listImagesTest(){
        
        Image image1 = new Image();
        Image image2 = new Image();
        
        List<Image> images = new ArrayList<>();
        
        image1.setIdImage(1L);
        image1.setName("hola.jpg");
        image1.setType("image/jpeg");
        
        String datos = "lala";
        byte[]data = datos.getBytes();
        image1.setBytes(data);
        
        image2.setIdImage(2L);
        image2.setName("lala.png");
        image2.setType("image/png");
        
        image2.setBytes(data);
        
        images.add(image1);
        images.add(image2);
        
        given(imageMock.findAll()).willReturn(images);
        
        List<Image> expected = imageService.listImages();
        
        assertEquals(expected, images);
    }
    
    @Test
    public void createImageTest(){
        
        Image image1 = new Image();
        
        image1.setIdImage(1L);
        image1.setName("hola.jpg");
        image1.setType("image/jpeg");
        
        String datos = "la";
        byte[]data2 = datos.getBytes();
        image1.setBytes(imageService.compressBytes(data2));
        
        given(imageMock.save(image1)).willAnswer
        (invocation -> invocation.getArgument(0));
        
        Image savedImage = imageMock.save(image1);
        
        assertThat(savedImage).isNotNull();
        
        verify(imageMock).save(any(Image.class));
    }
    
    @Test
    public void findImageTest(){
        
        Image image1 = new Image();

        image1.setIdImage(1L);
        image1.setName("hola.jpg");
        image1.setType("image/jpeg");
        
        String datos = "lala";
        byte[]data = datos.getBytes();
        image1.setBytes(data);
        
        given(imageMock.findById(image1.getIdImage()))
                .willReturn(Optional.of(image1));

        verify(imageMock, never()).save(any(Image.class));
    }
    
    @Test
    public void findImageByNameTest(){
        
        Image image1 = new Image();

        image1.setIdImage(1L);
        image1.setName("hola.jpg");
        image1.setType("image/jpeg");
        
        String datos = "lala";
        byte[]data = datos.getBytes();
        image1.setBytes(data);
        
        List<Image> images = new ArrayList();
        images.add(image1);
        
        given(imageMock.findByName(image1.getName()))
                .willReturn(List.of(image1));

        verify(imageMock, never()).save(any(Image.class));
    }
    
    @Test
    public void deleteImageTest(){
        
        Image image1 = new Image(); 
        
        image1.setIdImage(1L);
        image1.setName("hola.jpg");
        image1.setType("image/jpeg");
        
        String datos = "lala";
        byte[]data = datos.getBytes();
        image1.setBytes(data);
        
        imageService.delete(image1);
        imageService.delete(image1);
        
        verify(imageMock, times(2)).delete(image1);
    }
}
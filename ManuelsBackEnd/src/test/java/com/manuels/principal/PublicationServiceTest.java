package com.manuels.principal;

import com.manuels.principal.dao.IImageDao;
import com.manuels.principal.dao.IPublicationDao;
import com.manuels.principal.service.PublicationService;
import com.manuels.principal.service.ImageService;
import com.manuels.principal.models.Publication;
import com.manuels.principal.models.Image;
import java.util.ArrayList;
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
public class PublicationServiceTest {
    
    @MockBean
    private IPublicationDao publicationMock;
    
    @MockBean
    private IImageDao imageMock;
    
    @InjectMocks
    private PublicationService publicationService;
    
    @InjectMocks
    private ImageService imageService;
    
    /*@Test
    public void listPublicationsTest(){
        
        Publication publication1 = new Publication();
        Publication publication2 = new Publication();
        
        List<Publication> publications = new ArrayList<>();
        
        Image aux = new Image();
        
        aux.setIdImage(1L);
        aux.setName("hola.jpg");
        aux.setType("image/jpeg");
        String string = "lala";
        byte[] data = string.getBytes();
        aux.setBytes(data);
        
        publication1.setDescription("djdj djkjdk");
        publication1.setDescription2("djdj djkjdk");
        publication1.setSubtitle("djdj djkjdk");
        publication1.setIdPublication(1L);
        publication1.setTitle("llalal");
        publication1.setImage(aux);
        
        publication2.setDescription("skk ksks");
        publication1.setDescription2("djdj djkjdk");
        publication1.setSubtitle("djdj djkjdk");
        publication1.setSubtitle("djdj djkjdk");
        publication2.setIdPublication(2L);
        publication2.setTitle("lals fkfk");
        publication2.setImage(aux);
        
        publications.add(publication1);
        publications.add(publication2);
        
        given(publicationMock.findAll()).willReturn(publications);
        
        List<Publication> expected = publicationService.listPublications();
        
        assertEquals(expected, publications);
    }*/
    
    @Test
    public void createPublicationTest(){
        
        Publication publication1 = new Publication();
        Publication publication2 = new Publication();
        
        Image aux = new Image();
        
        aux.setName("hola.jpg");
        aux.setType("image/jpeg");
        String string = "lala";
        byte[] data = string.getBytes();
        aux.setBytes(data);
        
        Image image = imageService.create(aux);
        
        publication1.setDescription("djdj djkjdk");
        publication1.setDescription3("djdj djkjdk");
        publication1.setIdPublication(1L);
        publication1.setSubtitle("djdj djkjdk");
        publication1.setTitle("llalal");
        publication1.setImage(image);
        
        given(publicationMock.save(publication1)).willAnswer
        (invocation -> invocation.getArgument(0));
        
        Publication savedDateC = publicationMock.save(publication1);
        
        assertThat(savedDateC).isNotNull();
        
        verify(publicationMock).save(any(Publication.class));
    }
    
    @Test
    public void findPublicationTest(){
        
        Publication publication1 = new Publication();
        Publication publication2 = new Publication();
        
        Image aux = new Image();
        
        aux.setIdImage(1L);
        aux.setName("hola.jpg");
        aux.setType("image/jpeg");
        String string = "lala";
        byte[] data = string.getBytes();
        aux.setBytes(data);

        publication1.setDescription("djdj djkjdk");
        publication1.setDescription2("djdj djkjdk");
        publication1.setSubtitle("djdj djkjdk");
        publication1.setIdPublication(1L);
        publication1.setTitle("llalal");
        publication1.setImage(aux);
        
        given(publicationMock.findById(publication1.getIdPublication()))
                .willReturn(Optional.of(publication1));

        verify(publicationMock, never()).save(any(Publication.class));
    }
    
    @Test
    public void deletePublicationTest(){
        
        Publication publication1 = new Publication();
        Publication publication2 = new Publication();
        
        Image aux = new Image();
        
        aux.setIdImage(1L);
        aux.setName("hola.jpg");
        aux.setType("image/jpeg");
        String string = "lala";
        byte[] data = string.getBytes();
        aux.setBytes(data);
        
        publication1.setDescription("djdj djkjdk");
        publication1.setDescription2("djdj djkjdk");
        publication1.setSubtitle("djdj djkjdk");
        publication1.setIdPublication(1L);
        publication1.setTitle("llalal");
        publication1.setImage(aux);
        
        publicationService.delete(publication1);
        publicationService.delete(publication1);
        
        verify(publicationMock, times(2)).delete(publication1);
    }
}
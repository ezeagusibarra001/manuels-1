package com.manuels.principal;

import com.manuels.principal.dao.IReviewDao;
import com.manuels.principal.models.Review;
import com.manuels.principal.service.ReviewService;
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
public class ReviewServiceTest {
    
    @MockBean
    private IReviewDao reviewMock;
    
    @InjectMocks
    private ReviewService reviewService;
    
    @Test
    public void listReviewsTest(){
        
        Review review1 = new Review();
        Review review2 = new Review();
        
        List<Review> reviews = new ArrayList<>();
      
        review1.setCommentary("comentarioooooooo");
        review1.setIdReview(1L);
        review1.setName("review lalal");
        review1.setValidate(false);
        
        review2.setCommentary("kaskks s ksk");
        review2.setIdReview(2L);
        review2.setName("ajsjsaj fkaks");
        
        reviews.add(review1);
        reviews.add(review2);
        review1.setValidate(true);

        given(reviewMock.findAll()).willReturn(reviews);
        
        List<Review> expected = reviewService.listReviews();
        
        assertEquals(expected, reviews);
    }
    
    @Test
    public void createReviewTest(){
        
        Review review1 = new Review();
  
        review1.setCommentary("comentarioooooooo");
        review1.setIdReview(1L);
        review1.setName("review lalal");
        review1.setValidate(false);

        given(reviewMock.save(review1)).willAnswer
        (invocation -> invocation.getArgument(0));
        
        Review savedReview = reviewMock.save(review1);
        
        assertThat(savedReview).isNotNull();
        
        verify(reviewMock).save(any(Review.class));
    }
    
    @Test
    public void findReviewTest(){
        
        Review review1 = new Review();
        
        review1.setCommentary("comentarioooooooo");
        review1.setIdReview(1L);
        review1.setName("review lalal");
        review1.setValidate(false);
        
        given(reviewMock.findById(review1.getIdReview()))
                .willReturn(Optional.of(review1));

        verify(reviewMock, never()).save(any(Review.class));
    }
    
    @Test
    public void deleteReviewTest(){
        
        Review review1 = new Review();

        review1.setCommentary("comentarioooooooo");
        review1.setIdReview(1L);
        review1.setName("review lalal");
        review1.setValidate(false);
        
        reviewService.delete(review1);
        reviewService.delete(review1);
        
        verify(reviewMock, times(2)).delete(review1);
    }
    
    public void setTrueTest(){
        
        Review review1 = new Review();

        review1.setCommentary("comentarioooooooo");
        review1.setIdReview(1L);
        review1.setName("review lalal");
        review1.setValidate(false);

        Review reviewTrue = reviewService.setTrue(review1.getIdReview());
        
        assertEquals(false, reviewTrue.getValidate());
    }
}
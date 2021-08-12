package com.manuels.principal.dao;

import com.manuels.principal.models.Lesson;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ILessonDao extends JpaRepository<Lesson, Long> {
    @Query(value = "SELECT *FROM lesson WHERE title LIKE %?1%", nativeQuery = true)
    public List<Lesson> findByName(String title);
}

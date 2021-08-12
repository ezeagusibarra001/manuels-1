package com.manuels.principal.controller;

import com.manuels.principal.exceptions.NotFoundException;
import com.manuels.principal.models.DateC;
import com.manuels.principal.models.Lesson;
import com.manuels.principal.service.DateService;
import com.manuels.principal.service.LessonService;

import java.util.ArrayList;
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

@RequestMapping("/api/lessons")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private DateService dateService;
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Lesson> create(@RequestBody Lesson lesson){

        List<DateC> dates = new ArrayList<DateC>();
        DateC date;

        for (DateC d : lesson.getDates()) {
            date = dateService.create(d);
            dates.add(date);
        }

        lesson.setDates(dates);
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.create(lesson));
    }

    @GetMapping
    public List<Lesson> listAll() {

        return lessonService.listLessons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> readById(@PathVariable(value = "id") Long idLesson) throws NotFoundException{

        Lesson lesson = lessonService.findWithId(idLesson);

        if (lesson == null) {
            throw new NotFoundException("Not found lesson");
        }
        return ResponseEntity.ok(lesson);
    }

    @GetMapping("/names/{title}")
    public ResponseEntity<List<Lesson>> readByName(@PathVariable(value = "title") String title) throws NotFoundException{

        List<Lesson> lessons = lessonService.findByName(title);

        if (lessons.isEmpty()) {
            throw new NotFoundException("Not found lesson");
        }
        return ResponseEntity.ok(lessons);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Lesson> delete(@PathVariable(value = "id") Long idLesson) throws NotFoundException{

        Lesson lesson = lessonService.findWithId(idLesson);

        if (lesson == null) {
            throw new NotFoundException("Not found lesson");
        }

        lessonService.delete(lesson);
        return ResponseEntity.ok().build();
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Lesson> update(@RequestBody Lesson lesson) {

        for (DateC d : lesson.getDates()) {
            dateService.update(d);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.update(lesson));
    }
}
package com.manuels.principal.controller;

import com.manuels.principal.exceptions.ApiExceptionHandler;
import com.manuels.principal.exceptions.BadRequestException;
import com.manuels.principal.exceptions.FieldAlreadyExistException;
import com.manuels.principal.exceptions.NotFoundException;
import com.manuels.principal.exceptions.UnauthorizedException;
import com.manuels.principal.models.DateC;
import com.manuels.principal.models.Lesson;
import com.manuels.principal.service.DateService;
import com.manuels.principal.service.LessonService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private DateService dateService;

    /*@RequestMapping(value = "/testExceptionHandling",
            method = RequestMethod.GET)
    public String testExceptionHandling(@RequestParam int code) {
        switch (code) {
            case 401:
                throw new UnauthorizedException("You are not authorized");
            case 404:
                throw new NotFoundException("Requested resource is not found.");
            case 400:
                throw new BadRequestException("Please provide resource id.");
            case 409:
                throw new FieldAlreadyExistException("Resource already exists in DB.");

            default:
                return "Yeah...No Exception.";
        }
    }*/
    
    
    @PostMapping("/lessons")
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

    @GetMapping("/lessons")
    public List<Lesson> listAll() {

        return lessonService.listLessons();
    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<Lesson> readById(@PathVariable(value = "id") Long idLesson) throws NotFoundException{

        Lesson lesson = lessonService.findWithId(idLesson);

        if (lesson == null) {
            
            throw new NotFoundException("Not found lesson");
            //return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lesson);
    }

    @GetMapping("/lessons/names/{title}")
    public ResponseEntity<List<Lesson>> readByName(@PathVariable(value = "title") String title) throws NotFoundException{

        List<Lesson> lessons = lessonService.findByName(title);

        if (lessons.isEmpty()) {
            throw new NotFoundException("Not found lesson");
        }
        return ResponseEntity.ok(lessons);
    }

    @DeleteMapping("lessons/{id}")
    public ResponseEntity<Lesson> delete(@PathVariable(value = "id") Long idLesson) throws NotFoundException{

        Lesson lesson = lessonService.findWithId(idLesson);

        if (lesson == null) {
            throw new NotFoundException("Not found lesson");
        }

        lessonService.delete(lesson);
        return ResponseEntity.ok().build();
    }

    @PutMapping("lessons/{id}")
    public ResponseEntity<Lesson> update(@RequestBody Lesson lesson) {

        for (DateC d : lesson.getDates()) {

            dateService.update(d);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.update(lesson));
    }
}

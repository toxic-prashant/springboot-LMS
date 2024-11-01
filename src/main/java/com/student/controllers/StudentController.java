package com.student.controllers;

import com.student.entities.Student;
import com.student.entities.Subject;
import com.student.services.StudentService;
import com.student.services.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/student")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @Autowired
  private SubjectService subjectService;


  @PostMapping
  public ResponseEntity<String> registerStudent(@RequestBody Student student) {
    String string = studentService.createStudent(student);
    return ResponseEntity.ok(string);
  }


  @PostMapping("/{studentId}/{subjectId}")
  public ResponseEntity<?> enrollSubject(@PathVariable long studentId,
      @PathVariable long subjectId) {
    Subject subject = subjectService.findById(subjectId)
        .orElseThrow(() -> new EntityNotFoundException("Subject not found"));
    studentService.enrollSubject(studentId, subject);
    return ResponseEntity.ok("Enrolled Successfully!");
  }

  @GetMapping("/hidden-feature/{number}")
  public ResponseEntity<String> getFunFact(@PathVariable int number) {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://numbersapi.com/" + number;
    String fact = restTemplate.getForObject(url, String.class);
    return ResponseEntity.ok(fact);
  }

}

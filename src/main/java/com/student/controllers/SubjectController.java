package com.student.controllers;

import com.student.entities.Subject;
import com.student.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

  @Autowired
  private SubjectService subjectService;

  // Create a new Subject
  @PostMapping
  public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
    Subject createdSubject = subjectService.createSubject(subject);
    return ResponseEntity.ok(createdSubject);
  }
}

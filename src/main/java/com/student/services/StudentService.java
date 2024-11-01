package com.student.services;

import com.student.entities.Student;
import com.student.entities.Subject;
import com.student.repositrories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  public Student enrollSubject(long studentId, Subject subject) {
    Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new EntityNotFoundException("Student not found"));
    student.getSubjects().add(subject);
    return studentRepository.save(student);
  }

  public String createStudent(Student student) {
    studentRepository.save(student);
    return "Student created successfully";
  }
}

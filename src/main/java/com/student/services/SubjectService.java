package com.student.services;

import com.student.entities.Subject;
import com.student.repositrories.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

  @Autowired
  private SubjectRepository subjectRepository;

  /**
   * Finds a Subject by its ID
   *
   * @param subjectId The ID of the subject
   * @return Optional containing the Subject if found
   * @throws EntityNotFoundException if the Subject is not found
   */
  public Optional<Subject> findById(Long subjectId) {
    return subjectRepository.findById(subjectId);
  }

  /**
   * Creates a new subject
   *
   * @param subject The subject to be created
   * @return The created subject
   */
  public Subject createSubject(Subject subject) {
    return subjectRepository.save(subject);
  }

  /**
   * Updates an existing subject
   *
   * @param subjectId      The ID of the subject to update
   * @param subjectDetails The updated details for the subject
   * @return The updated subject
   */
  public Subject updateSubject(Long subjectId, Subject subjectDetails) {
    Subject subject = subjectRepository.findById(subjectId)
        .orElseThrow(() -> new EntityNotFoundException("Subject not found"));

    subject.setName(subjectDetails.getName());
    // Additional fields can be set here if needed

    return subjectRepository.save(subject);
  }

  /**
   * Deletes a subject by its ID
   *
   * @param subjectId The ID of the subject to delete
   */
  public void deleteSubject(Long subjectId) {
    Subject subject = subjectRepository.findById(subjectId)
        .orElseThrow(() -> new EntityNotFoundException("Subject not found"));
    subjectRepository.delete(subject);
  }

  /**
   * Retrieves all subjects
   *
   * @return List of all subjects
   */
  public List<Subject> getAllSubjects() {
    return subjectRepository.findAll();
  }
}

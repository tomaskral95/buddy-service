package com.buddyservice.service;

import com.buddyservice.domain.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Rozhraní servisní třídy pro práci s entitou Student.
 */
public interface IStudentService {
    void saveStudent(Student student);
    boolean authenticateStudent(String rodneCislo, String heslo);
    Student findStudent(String rodneCislo);
    List<Student> findAllStudents();
    List<Student> findStudentsOfBuddy(Student student);
    List<Student> findBuddies();
    List<Student> findAllForeignStudents();
}

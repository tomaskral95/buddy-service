package com.buddyservice.service;

import com.buddyservice.domain.Student;

import java.util.List;

public interface IStudentService {
    void saveStudent(Student student);
    boolean authenticateStudent(String rodneCislo, String heslo);
    Student findStudent(String rodneCislo);
    List<Student> findAllStudents();
}

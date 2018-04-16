package com.buddyservice.service;

import com.buddyservice.domain.Student;

public interface IStudentService {
    void saveStudent(Student student);
    boolean authenticateStudent(String rodneCislo, String heslo);

}

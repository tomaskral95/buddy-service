package com.buddyservice.service;

import com.buddyservice.domain.Student;
import com.buddyservice.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {

    private IStudentRepository buddyRepository;

    @Autowired
    public StudentService(IStudentRepository buddyRepository) {
        this.buddyRepository = buddyRepository;
    }

    public void saveBuddy(Student student) {
        buddyRepository.save(student);
    }

}

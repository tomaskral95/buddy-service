package com.buddyservice.service;

import com.buddyservice.auth.IAuthService;
import com.buddyservice.domain.Student;
import com.buddyservice.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Service
@Transactional
public class StudentService implements IStudentService{

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IAuthService authService;

    public void saveStudent(Student student) {
        try {
            student.setHeslo(authService.createMD5Hash(student.getHeslo()));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Zvoleno nesprávné kódování");
        }
        studentRepository.save(student);
    }

    public boolean authenticateStudent(String rodneCislo, String heslo) {
        Student foundStudent = studentRepository.findStudentByRodneCislo(rodneCislo);
        if (foundStudent == null) {
            return false;
        }

        String md5Pass = "";
        try {
            md5Pass = authService.createMD5Hash(heslo);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Zvoleno nesprávné kódování");
        }

        return foundStudent.getHeslo().equals(md5Pass) && foundStudent.getRodneCislo().equals(rodneCislo);
    }

}

package com.buddyservice.repository;

import com.buddyservice.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {

    Student findStudentByRodneCislo(String rodneCislo);

    @Query("select s from Student s where s.buddy = :student")
    List<Student> findStudentsOfBuddy(@Param("student") Student student);

    @Query("select s from Student s where s.zahranicni = 0")
    List<Student> findBuddies();

    @Query("select s from Student s where s.zahranicni = 1")
    List<Student> findAllForeignStudents();

}
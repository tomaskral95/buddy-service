package com.buddyservice.repository;

import com.buddyservice.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {

    @Override
    Optional<Student> findById(Long aLong);
}

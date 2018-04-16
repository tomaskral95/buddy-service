package com.buddyservice.repository;

import com.buddyservice.domain.Akce;
import com.buddyservice.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAkceRepository extends JpaRepository<Akce, Long> {

    @Override
    Optional<Akce> findById(Long aLong);

}

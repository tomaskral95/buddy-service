package com.buddyservice.repository;

import com.buddyservice.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Rozhraní poskytující přístup k entitám k databázi na základě metod definovaných tímto rozhraním, nebo metod ručně
 * dodefinovaných a následně použitých v servisní vrstvě aplikace. Toto rozhraní poskytuje propojení s entitou Student.
 */
@Repository
public interface IStudentRepository extends JpaRepository<Student, Long> {

    /**
     * Vrátí objekt entity Student na základě vloženého rodného čísla.
     * @param rodneCislo
     * @return Objekt entity Student
     */
    Student findStudentByRodneCislo(String rodneCislo);

    /**
     * Vrátí kolekci studentů, kteří náleží buddymu, který byl zadán jako parametr funkce
     * @param student
     * @return Kolekce objektů entity Student
     */
    @Query("select s from Student s where s.buddy = :student")
    List<Student> findStudentsOfBuddy(@Param("student") Student student);

    @Query("select s from Student s where s.zahranicni = 0")
    List<Student> findBuddies();

    @Query("select s from Student s where s.zahranicni = 1")
    List<Student> findAllForeignStudents();

}
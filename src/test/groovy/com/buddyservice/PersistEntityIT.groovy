package com.buddyservice

import com.buddyservice.domain.Adresa
import com.buddyservice.domain.Pohlavi
import com.buddyservice.domain.Student
import com.buddyservice.repository.IStudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.util.Date

@ContextConfiguration(locations = ["classpath:jdbc-connection-test-context.xml"])
class PersistEntityIT extends Specification {

    @Autowired
    private IStudentRepository buddyRepository

    def "Test connection to db through JpaRepository"() {
        given: "Preparing data for database persist test"
            def student = new Student(
                    xname: "krat08",
                    jmeno: "Tomáš",
                    prijmeni: "Král",
                    datumNarozeni: new Date(1995,12,12),
                    pohlavi: Pohlavi.MUZ,
                    statniPrislusnost: "CZ",
                    adresa: new Adresa(
                            stat: "Česká Republika",
                            mesto: "Praha",
                            ulice: "Za rohem",
                            cisloPopisne: 22
                    ),
                    telefon: 731373927,
                    email: "krat08@vse.cz"
            )
        when: "Persist and select from database"
            buddyRepository.save(student)
            def foundStudents = buddyRepository.findAll()
            def obtainedStudent = foundStudents.get(0)
        then: "Compare persisted and obtained object"
            assert buddyRepository != null
            assert foundStudents.size() != 0

            assert student.xname == obtainedStudent.xname
            assert student.jmeno == obtainedStudent.jmeno
            assert student.prijmeni == obtainedStudent.prijmeni
            assert student.pohlavi == obtainedStudent.pohlavi
            assert student.statniPrislusnost == obtainedStudent.statniPrislusnost
            assert student.telefon == obtainedStudent.telefon
            assert student.email == obtainedStudent.email

            // Comparing ADDRESS 1
            assert student.adresa.stat == obtainedStudent.adresa.stat
            assert student.adresa.mesto == obtainedStudent.adresa.mesto
            assert student.adresa.ulice == obtainedStudent.adresa.ulice
            assert student.adresa.cisloPopisne == obtainedStudent.adresa.cisloPopisne

    }

}

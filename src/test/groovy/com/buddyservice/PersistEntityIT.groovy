package com.buddyservice

import com.buddyservice.domain.Adresa
import com.buddyservice.domain.Pohlavi
import com.buddyservice.domain.Student
import com.buddyservice.repository.IStudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:jdbc-connection-test-context.xml"])
class PersistEntityIT extends Specification{

    @Autowired
    private IStudentRepository buddyRepository

    def "Test connection to db through JpaRepository"() {
        given:"Preparing data for database persist test"
            def student = new Student(
                    xname: "krat08",
                    jmeno: "Tomáš",
                    prijmeni: "Král",
                    pohlavi: Pohlavi.MUZ,
                    statniPrislusnost: "CZ",
                    adresy: [
                            new Adresa(
                                    stat: "Česká Republika",
                                    mesto: "Praha",
                                    ulice: "Za rohem",
                                    cisloPopisne: 22
                            ),
                            new Adresa(
                                    stat: "California",
                                    mesto: "Los Angeles",
                                    ulice: "Venice",
                                    cisloPopisne: 33
                            )
                    ],
                    telefon: 731373927,
                    email: "krat08@vse.cz"
            )
        when:"Persist and select from database"
            buddyRepository.save(student)
            def foundStudent = buddyRepository.findAll()
        then:""
            assert buddyRepository != null
            assert foundStudent.size() > 0
    }

}

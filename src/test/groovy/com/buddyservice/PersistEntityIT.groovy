package com.buddyservice

import com.buddyservice.domain.Adresa
import com.buddyservice.domain.Pohlavi
import com.buddyservice.domain.Student
import com.buddyservice.repository.IStudentRepository
import org.h2.tools.Server
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import java.sql.SQLException

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
        when: "Persist and select from database"
            startH2TcpServer()
            buddyRepository.save(student)
            def foundStudents = buddyRepository.findAll()
            def obtainedStudent = foundStudents.get(0)
        then: "Compare persisted and obtained object"
            assert buddyRepository != null
            assert foundStudents.size() > 0

            assert student.xname == obtainedStudent.xname
            assert student.jmeno == obtainedStudent.jmeno
            assert student.prijmeni == obtainedStudent.prijmeni
            assert student.pohlavi == obtainedStudent.pohlavi
            assert student.statniPrislusnost == obtainedStudent.statniPrislusnost
            assert student.telefon == obtainedStudent.telefon
            assert student.email == obtainedStudent.email

            // Comparing ADDRESS 1
            assert student.adresy[0].stat == obtainedStudent.adresy[0].stat
            assert student.adresy[0].mesto == obtainedStudent.adresy[0].mesto
            assert student.adresy[0].ulice == obtainedStudent.adresy[0].ulice
            assert student.adresy[0].cisloPopisne == obtainedStudent.adresy[0].cisloPopisne

            // Comparing ADDRESS 2
            assert student.adresy[1].stat == obtainedStudent.adresy[1].stat
            assert student.adresy[1].mesto == obtainedStudent.adresy[1].mesto
            assert student.adresy[1].ulice == obtainedStudent.adresy[1].ulice
            assert student.adresy[1].cisloPopisne == obtainedStudent.adresy[1].cisloPopisne
    }

    def void startH2TcpServer() {
        try {
            Server server = Server.createTcpServer().start()
            println "Server started and connection is open."
            println "URL: jdbc:h2:" + server.getURL() + "/mem:test"
        } catch (SQLException e) {
            throw new IllegalStateException("Could not start H2 server", e)
        }
    }

}

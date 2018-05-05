package com.buddyservice

import com.buddyservice.domain.Adresa
import com.buddyservice.domain.Akce
import com.buddyservice.domain.DruhAkce
import com.buddyservice.domain.Pohlavi
import com.buddyservice.domain.Student
import com.buddyservice.repository.IStudentRepository
import com.buddyservice.service.IStudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:jdbc-connection-test-context.xml", "classpath:service-test-context.xml"])
class PersistEntityIT extends Specification {

    @Autowired
    private IStudentService studentService

    @Autowired
    private IStudentRepository studentRepository

    def "Test connection to db through JpaRepository"() {
        given: "Preparing data for database persist test"
            def adresa = new Adresa(
                    stat: "Ceska Republika",
                    mesto: "Praha",
                    ulice: "Nad Sazavou",
                    cisloPopisne: 11
            )
            def adresa2 = new Adresa(
                    stat: "Česká Republika",
                    mesto: "Praha",
                    ulice: "Za rohem",
                    cisloPopisne: 22
            )
            def druhAkce = new DruhAkce(
                    druh: "Volný čas"
            )
            def akce = [
                    new Akce(
                            druhAkce: druhAkce,
                            nazev: "Fakultni koncert",
                            datum: "1.1.2000",
                            casOd: "18:00",
                            casDo: "22:00",
                            misto: adresa,
                            popis: "Proste zabava",
                            cena: 80,
                            kapacita: 3
                    ),
                    new Akce(
                            druhAkce: druhAkce,
                            nazev: "Nejaka dalsi sranda",
                            datum: "2.2.2000",
                            casOd: "18:00",
                            casDo: "22:00",
                            misto: adresa,
                            popis: "Proste zabava",
                            cena: 80,
                            kapacita: 10
                    )
            ]
            def student = new Student(
                    rodneCislo: "9410110235",
                    xname: "krat08",
                    jmeno: "Tomáš",
                    prijmeni: "Král",
                    pohlavi: Pohlavi.MUZ,
                    statniPrislusnost: "CZ",
                    adresa: adresa,
                    telefon: 731373927,
                    email: "krat08@vse.cz",
                    akce: akce,
                    heslo: "heslo",
                    datumNarozeni: "19951212",
                    admin: true
            )
        when: "Persist and select from database"
            studentService.saveStudent(student)
            def foundStudents = studentRepository.findAll()
            def obtainedStudent = foundStudents.get(0)
        then: "Compare persisted and obtained object"
            assert studentRepository != null
            assert foundStudents.size() > 0

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

package com.buddyservice.repository;

import com.buddyservice.domain.Akce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Rozhraní poskytující přístup k entitám k databázi na základě metod definovaných tímto rozhraním, nebo metod ručně
 * dodefinovaných a následně použitých v servisní vrstvě aplikace. Toto rozhraní poskytuje propojení s entitou Akce.
 */
@Repository
public interface IAkceRepository extends JpaRepository<Akce, Long> {

    /**
     * Metoda vráti objekt entity na základě číselného ID v parametru.
     * @param id
     * @return Objekt požadované entity Akce
     */
    Akce findAkceByIdAkce(Long id);

}

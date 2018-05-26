package com.buddyservice.repository;

import com.buddyservice.domain.DruhAkce;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Rozhraní poskytující přístup k entitám k databázi na základě metod definovaných tímto rozhraním, nebo metod ručně
 * dodefinovaných a následně použitých v servisní vrstvě aplikace. Toto rozhraní poskytuje propojení s entitou DruhAkce.
 */
public interface IDruhAkceRepository extends JpaRepository<DruhAkce, Long> {
}

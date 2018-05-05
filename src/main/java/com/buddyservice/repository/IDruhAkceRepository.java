package com.buddyservice.repository;

import com.buddyservice.domain.Adresa;
import com.buddyservice.domain.DruhAkce;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDruhAkceRepository extends JpaRepository<DruhAkce, Long> {
}

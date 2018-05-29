package com.buddyservice.service;

import com.buddyservice.domain.Akce;

import java.util.List;

/**
 * Rozhraní servisní třídy pro práci s entitou Akce.
 */
public interface IAkceService {

    void saveAkce(Akce akce);
    List<Akce> findAll();
    Akce findAkceById(Long id);
}

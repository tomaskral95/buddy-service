package com.buddyservice.service;

import com.buddyservice.domain.DruhAkce;

import java.util.List;

/**
 * Rozhraní servisní třídy pro práci s entitou DruhAkce.
 */
public interface IDruhAkceService {
    List<DruhAkce> getDruhyAkce();
}

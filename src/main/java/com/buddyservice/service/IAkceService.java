package com.buddyservice.service;

import com.buddyservice.domain.Akce;

import java.util.List;

public interface IAkceService {

    void saveAkce(Akce akce);
    List<Akce> findAll();
    Akce findAkceById(Long id);
}

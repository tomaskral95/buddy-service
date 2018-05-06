package com.buddyservice.service;

import com.buddyservice.domain.Akce;
import com.buddyservice.repository.IAkceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("akceService")
@Transactional
public class AkceService implements IAkceService {

    @Autowired
    private IAkceRepository akceRepository;

    public void saveAkce(Akce akce) {
        akceRepository.save(akce);
    }

    public List<Akce> findAll() {
        return akceRepository.findAll();
    }

    @Override
    public Akce findAkceById(Long id) {
        return akceRepository.findAkceByIdAkce(id);
    }
}

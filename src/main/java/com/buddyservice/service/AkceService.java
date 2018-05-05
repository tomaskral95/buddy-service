package com.buddyservice.service;

import com.buddyservice.domain.Akce;
import com.buddyservice.repository.IAkceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("akceService")
@Transactional
public class AkceService implements IAkceService {

    @Autowired
    private IAkceRepository akceRepository;

    @Override
    public void saveAkce(Akce akce) {
        akceRepository.save(akce);
    }
}

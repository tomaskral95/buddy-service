package com.buddyservice.service;

import com.buddyservice.domain.Akce;
import com.buddyservice.domain.DruhAkce;
import com.buddyservice.repository.IAkceRepository;
import com.buddyservice.repository.IDruhAkceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("druhAkceService")
@Transactional
public class DruhAkceService implements IDruhAkceService {

    @Autowired
    private IDruhAkceRepository druhAkceRepository;

        @Override
    public List<DruhAkce> getDruhyAkce() {
        return druhAkceRepository.findAll();
    }
}

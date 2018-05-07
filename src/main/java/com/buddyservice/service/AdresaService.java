package com.buddyservice.service;

import com.buddyservice.domain.Adresa;
import com.buddyservice.domain.Akce;
import com.buddyservice.repository.IAdresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adresaService")
@Transactional
public class AdresaService implements IAdresaService {

    @Autowired
    private IAdresaRepository adresaRepository;

    public void saveAdresa(Adresa adresa) {
        adresaRepository.save(adresa);
    }
}

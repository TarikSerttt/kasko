package com.example.kasko_firmasi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.kasko_firmasi.model.PersonInfo;
import com.example.kasko_firmasi.repository.PersonInfoRepository;

@Service
public class PersonInfoService {

    private final PersonInfoRepository personInfoRepository;

    @Autowired
    public PersonInfoService(PersonInfoRepository personInfoRepository) {
        this.personInfoRepository = personInfoRepository;
    }

    // Kişisel bilgileri veritabanına kaydetme
    public PersonInfo savePersonInfo(PersonInfo personInfo) {
        return personInfoRepository.save(personInfo);
    }

    // Kişisel bilgileri ID ile bulma
    public PersonInfo getPersonInfoById(Long id) {
        return personInfoRepository.findById(id).orElse(null);
    }

    // Tüm kişisel bilgileri listeleme
    public Iterable<PersonInfo> getAllPersonInfo() {
        return personInfoRepository.findAll();
    }
}

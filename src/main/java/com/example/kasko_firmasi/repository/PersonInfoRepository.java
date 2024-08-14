package com.example.kasko_firmasi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.kasko_firmasi.model.PersonInfo;

public interface PersonInfoRepository extends JpaRepository<PersonInfo, Long> {

}

package com.example.kasko_firmasi.repository;

import com.example.kasko_firmasi.model.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<PersonInfo, Long> {

}

package com.example.kasko_firmasi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.kasko_firmasi.repository")
@EntityScan(basePackages = "com.example.kasko_firmasi.model")
public class KaskoFirmasiApplication {
    public static void main(String[] args) {
        SpringApplication.run(KaskoFirmasiApplication.class, args);
    }
}

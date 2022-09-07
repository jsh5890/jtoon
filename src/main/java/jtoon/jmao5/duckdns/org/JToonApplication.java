package jtoon.jmao5.duckdns.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JToonApplication {

    public static void main(String[] args) {
        SpringApplication.run(JToonApplication.class, args);
    }

}

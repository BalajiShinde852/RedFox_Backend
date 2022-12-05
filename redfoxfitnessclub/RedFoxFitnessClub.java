package com.recruitapp.redfoxfitnessclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RedFoxFitnessClub {

    public static void main(String[] args) {
	SpringApplication.run(RedFoxFitnessClub.class, args);
    }

}

package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student juan = new Student(
                    "Juan",
                    "juanmangubat@gmail.com",
                    LocalDate.of(2000, Month.AUGUST, 11),
                    22
            );

            Student katie = new Student(
                    "Katie",
                    "ktmang@gmail.com",
                    LocalDate.of(1999, Month.APRIL, 15),
                    23
            );

            repository.saveAll(
                    List.of(juan, katie)
            );
        };
    }
}

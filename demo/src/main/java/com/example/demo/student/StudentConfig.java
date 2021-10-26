package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.util.Calendar.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student mary = new Student(
                    "Mary",
                    "mary@gmail.com",
                    LocalDate.of(2000, 1, 03)
            );
            Student mike = new Student (
                    "Mike",
                    "mike@hotmail.com",
                    LocalDate.of(1990, 3, 18)
            );
            Student mario = new Student("Mario", "mario@gmail.com", LocalDate.of(1990, 4, 16));

            repository.saveAll(List.of(mary, mike, mario));
        };
    }
}

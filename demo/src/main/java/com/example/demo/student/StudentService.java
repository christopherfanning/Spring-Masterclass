package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;

public class StudentService {

    public static List<Student> getStudents(){
        return List.of(new Student (1L,
                "Bobby",
                "thing@donk.com",
                LocalDate.of(1990, 1, 13),
                32
        ));
    }
}

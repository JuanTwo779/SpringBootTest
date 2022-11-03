package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class StudentService {

    public List<Student> getStudents(){
        return List.of(
                new Student(
                        1L,
                        "Juan",
                        "juanmangubat@gmail.com",
                        LocalDate.of(2000, Month.AUGUST, 11),
                        22
                )
        );
    }
}

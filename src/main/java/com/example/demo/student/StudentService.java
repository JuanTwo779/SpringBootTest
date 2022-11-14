package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service //service provider class - spring bean
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired //injects field to constructor instead of having to create new instance
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.
                findStudentByEmail(student.getEmail());

        //verify if student email is already in DB if not, save it
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email already registered");
        }else {
            studentRepository.save(student);
        }
    }
}

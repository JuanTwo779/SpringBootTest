package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //API layer - class for handling requests
@RequestMapping(path = "api/v2/student") //Handles mapping HTTP requests to methods in Rest Controller
public class StudentController {

    private final StudentService studentService;

    @Autowired //dependency injection (gets rid of need to know how to construct object)
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping//acquire data from DB
    public List<Student> getStudents(){
        return studentService.getStudents();
        //get method to acquire data from DB
    }

    @PostMapping//sends payload to server DB
    public void registerNewStudent (@RequestBody Student student){
        //RequestBody maps student from client into parameter student
        studentService.addNewStudent(student);
    }
}

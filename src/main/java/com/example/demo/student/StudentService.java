package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
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

    public void deleteStudent(Long studentId) {
//        studentRepository.findById(studentId);

        boolean idExists = studentRepository.existsById(studentId);
        if (!idExists){
            throw new IllegalStateException("student with id " +
                    studentId + " does not exist");
        }else {
            studentRepository.deleteById(studentId);
        }
    }

    @Transactional //entity goes into managed state i.e., the Student object
    public void updateStudent(Long studentId, String name, String email) {
        //check if student exists

        //make an object to make comparisons for validation
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalStateException("Student with id " + studentId + " does not exist"));

        if (name != null && name.length() > 0 && name != student.getName()){
            student.setName(name);
        }

        if (email != null && email.length() > 0 && email != student.getEmail()){
            //check if email is already used
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("Student with email " + email + " has already been used");
            }else {
                student.setEmail(email);
            }
        }



    }
}

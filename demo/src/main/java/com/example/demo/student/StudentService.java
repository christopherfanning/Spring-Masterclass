package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("No student with id " + studentId + "exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        // Somehow use getters and setters from class to change values in database.

        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new IllegalStateException("Cannot find student with id " + studentId + "."));

        if ( name != null && name.length() > 0 && !name.equals(student.getName())){
            // Update the name.
            student.setName(name);
            System.out.println("Updated the name");
        } else {
            System.out.println(" The name was the same or something.");
        }
            if (email != null && email.length() > 0 && !email.equals(student.getEmail())){
            // update the email
                Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
                if(studentOptional.isPresent()){
                    throw new IllegalStateException("Email taken");
                }
            student.setEmail(email);
            System.out.println("Updated the email");
        }
    }
}

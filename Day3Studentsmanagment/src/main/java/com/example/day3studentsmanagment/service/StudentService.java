package com.example.day3studentsmanagment.service;

import com.example.day3studentsmanagment.model.StudentModel;
import com.example.day3studentsmanagment.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private StudentRepository repository;
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }
    public StudentModel addStudent(StudentModel student){
        return repository.save(student);

    }

}

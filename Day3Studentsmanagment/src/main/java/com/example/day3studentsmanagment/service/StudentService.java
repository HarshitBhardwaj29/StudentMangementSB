package com.example.day3studentsmanagment.service;

import com.example.day3studentsmanagment.dto.StudentRequestDto;
import com.example.day3studentsmanagment.dto.StudentResponseDto;
import com.example.day3studentsmanagment.exception.StudentNotFoundException;
import com.example.day3studentsmanagment.model.StudentModel;
import com.example.day3studentsmanagment.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository repository;
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }
    public StudentResponseDto addStudent(StudentRequestDto dto){
        StudentModel student = new StudentModel();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());
        StudentModel saved = repository.save(student);
        return new StudentResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail()
        );
    }

    public List<StudentResponseDto> getStudent(){

        return repository.findAll()
                .stream()
                .map(s->new StudentResponseDto(
                        s.getId(),
                        s.getName(),
                        s.getAge(),
                        s.getEmail()
                )).toList();
    }
    //update student
    public StudentResponseDto updateStudent(String id,StudentRequestDto student){
        StudentModel existingStudent = repository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found with id: "));
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());

        StudentModel saved = repository.save(existingStudent);
        return  new StudentResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail()
        );


    }
    //delete student
    public void deleteStudent(String id){
        StudentModel existingStudent = repository.findById(id).orElseThrow(() -> new StudentNotFoundException("No student found"));
        repository.deleteById(id);
    }

    public  StudentResponseDto patchStudent(String id, StudentRequestDto student){
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with id: " + id
                ));

        if(student.getName() != null){
            existingStudent.setName(student.getName());
        }
        if(student.getAge() != 0){
            existingStudent.setAge(student.getAge());
        }
        if(student.getEmail() != null){
            existingStudent.setEmail(student.getEmail());
        }

        StudentModel saved = repository.save(existingStudent);

        return new StudentResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail()
        );
    }}



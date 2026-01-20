package com.example.day3studentsmanagment.repository;

import com.example.day3studentsmanagment.model.StudentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository  extends MongoRepository <StudentModel,String>{

}

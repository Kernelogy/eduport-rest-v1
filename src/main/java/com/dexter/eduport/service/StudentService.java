package com.dexter.eduport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dexter.eduport.persist.model.Student;
import com.dexter.eduport.persist.repo.StudentRepo;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    public Student findById(int id){
        return studentRepo.findById(id).get();
    }
}

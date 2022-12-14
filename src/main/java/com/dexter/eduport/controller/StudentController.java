package com.dexter.eduport.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dexter.eduport.persist.model.Student;
import com.dexter.eduport.persist.repo.StudentRepo;
import com.dexter.eduport.service.StudentService;
import com.fasterxml.jackson.core.format.InputAccessor.Std;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/home")
    public ResponseEntity<?> home(){
        return ResponseEntity
        .status(HttpStatus.OK)
        .body("Welcome");
    }
    @PostMapping(value = "/add")
    public ResponseEntity<?> addStudent(@RequestBody final Student student){
        System.out.println(student.getName());
        studentRepo.saveAndFlush(student);        
        return ResponseEntity
        .status(HttpStatus.OK)
        .body("OK");
    }
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> getStudent(@PathVariable final int id){ 
        // Optional<Student> student = studentRepo.findById(id);
        Student student = studentService.findById(id);        
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(student);
    }
    @GetMapping(value = "/getByName/{name}")
    public ResponseEntity<?> getStudent(@PathVariable final String name){ 
        Student student = studentRepo.findByName(name);
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(student);
    }    
    @GetMapping(value = "/getByAge/{age}")
    public ResponseEntity<?> getStudentsByAge(@PathVariable final int age){ 
        List<Student> students = studentRepo.findAllByAge(age);
        
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(students);
    }     
    @GetMapping(value = "/get/all")
    public ResponseEntity<?> getAllStudents(){ 
        List<Student> students = studentRepo.findAll();
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(students);
    }
    @PostMapping(value = "/update")
    public ResponseEntity<?> updateStudent(@RequestBody final Student dto){
        Student student = studentRepo.findById(dto.getId()).get();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setGender(dto.getGender());
        studentRepo.saveAndFlush(student);        
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(student);
    }
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable final int id){ 
        Student student = studentRepo.findById(id).get();        
        // studentRepo.deleteById(id);
        studentRepo.delete(student);
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(student);
    }    
}
//curl -H 'Content-Type: application/json' -d '{ "name":"Mukil","age":"38", "gender": "male"}' -X POST http://localhost:8088/student/add
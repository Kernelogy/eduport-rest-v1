package com.dexter.eduport.persist.repo;


import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dexter.eduport.persist.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{
    @Query(value = "FROM Student s WHERE s.name = ?1")
    Student findByName(String name);

    // @Query(value = "FROM Student s WHERE s.age > ?1")
    // List<Student> findAllByAge(int age);

    // @Query(value = "FROM Student s WHERE s.age > :age")//named parameter
    // List<Student> findAllByAge(int age);

    @Query(value = "SELECT * FROM Students WHERE age > :age", nativeQuery = true)//named parameter
    List<Student> findAllByAge(int age);

}

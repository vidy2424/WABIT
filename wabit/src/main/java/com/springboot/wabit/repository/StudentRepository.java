package com.springboot.wabit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.wabit.dto.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}

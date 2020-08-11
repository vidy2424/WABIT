package com.springboot.wabit.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.wabit.dto.Student;
import com.springboot.wabit.repository.StudentRepository;
 
@Repository
public class StudentDAO {

	@Autowired
	private StudentRepository studentRepository;

	public List<Student> get() {
		return studentRepository.findAll();
	}

	public Student save(Student student) {
		return studentRepository.save(student);
	}

	public void delete(int id) {
		studentRepository.deleteById(id);
	}
}

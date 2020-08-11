package com.springboot.wabit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.wabit.dao.StudentDAO;
import com.springboot.wabit.dto.Student;

@Service
public class StudentService {

	@Autowired
	private StudentDAO studentDAO;

	public List<Student> get() {
		return studentDAO.get();
	}

	public Student save(Student student) {
		return studentDAO.save(student);
	}

	public void delete(int id) {
		studentDAO.delete(id);
	}
}

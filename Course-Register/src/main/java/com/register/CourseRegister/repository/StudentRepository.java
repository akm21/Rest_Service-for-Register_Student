package com.register.CourseRegister.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.register.CourseRegister.model.Student;
public interface StudentRepository extends JpaRepository<Student, Integer>
{

	Student findById(String studentId);
}

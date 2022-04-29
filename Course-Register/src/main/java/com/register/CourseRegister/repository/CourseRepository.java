package com.register.CourseRegister.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.register.CourseRegister.model.Course;
public interface CourseRepository extends JpaRepository<Course, Integer>
{
	
	public Course findById(String studentId);
}

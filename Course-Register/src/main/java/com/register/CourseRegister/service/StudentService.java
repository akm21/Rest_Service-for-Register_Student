package com.register.CourseRegister.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.register.CourseRegister.exception.CustomException;
import com.register.CourseRegister.model.Course;
import com.register.CourseRegister.model.Student;
import com.register.CourseRegister.repository.CourseRepository;
import com.register.CourseRegister.repository.StudentRepository;

@Component
public class StudentService {
	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;
	private static List<Student> students = new ArrayList<>();

	public List<Student> retrieveAllStudents() {
		List<Student> student = new ArrayList<Student>();
		studentRepository.findAll().forEach(student1 -> student.add(student1));
		return student;
	}

	public Student retrieveStudent(String studentId) throws CustomException {
		try {
			return studentRepository.findById(studentId);
		} catch (Exception e) {
			throw new CustomException("could not find student "+e);
		}
		
	}

	public Course retrieveCourses(String courseId) throws CustomException {
		
		try {
			return courseRepository.findById(courseId);
		} catch (Exception e) {
			throw new CustomException("could not find student "+e);
		}

	}

	public Course addCourse(Course course) throws CustomException {
		try {
			Course coursesaved = courseRepository.save(course);
			return coursesaved;
		} catch (Exception e) {
			throw new CustomException("could not find student "+e);
		}

	}

	public List<Course> getAllCourse() throws CustomException {
		try {
			List<Course> findAll = courseRepository.findAll();
			return findAll;
		} catch (Exception e) {
			throw new CustomException("could not find Course "+e);
		}
		

	}

	public Student addStudent(Student student) throws CustomException {
		try {
			return studentRepository.save(student);
		} catch (Exception e) {
			throw new CustomException("could not find student "+e);
		}
		
	}

	public List<Student> getAllStudent() throws CustomException {
		try {
			return studentRepository.findAll();
		} catch (Exception e) {
			throw new CustomException("could not find student "+e);
		}
	}
	
	

	public void  deleteStudent(String studentId) throws CustomException {
		Student student = studentRepository.findById(studentId);
		if (student == null) {
			throw new CustomException("Student Not Found");
		}
		try {
			studentRepository.delete(student);
		} catch (Exception e) {
			throw new CustomException("could not delete student "+e);
		}
	}

	public void deleteCourse(String courseId) throws CustomException {
		Course course	= courseRepository.findById(courseId);
		if (course == null) {
			throw new CustomException("course Not Found");

		}
		try {
			courseRepository.delete(course);
		} catch (Exception e) {
			throw new CustomException("could not delete course "+e);
		}
	}

	public Student addCourseToExistingStudent(String studentId, Course newCourse) throws CustomException {
		Student student = studentRepository.findById(studentId);
		if (student == null) {
			throw new CustomException("Student Not Found");
		}
		try {
			student.getCourses().add(newCourse);
			return studentRepository.save(student);
		} catch (Exception e) {
			throw new CustomException("could not delete student "+e);
		}
	}

	public Course retrieveCourse(String courseId) throws CustomException {
		Course course = courseRepository.findById(courseId);
		if (course == null) {
			throw new CustomException("course Not Found");
		}
		try {
			return course;
		} catch (Exception e) {
			throw new CustomException("could not delete student "+e);
		}
	}
}

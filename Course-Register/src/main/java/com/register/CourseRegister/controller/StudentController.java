package com.register.CourseRegister.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.register.CourseRegister.exception.CustomException;
import com.register.CourseRegister.model.Book;
import com.register.CourseRegister.model.Course;
import com.register.CourseRegister.model.Student;
import com.register.CourseRegister.model.Userdata;
import com.register.CourseRegister.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private RestTemplateBuilder restTemplate;

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@GetMapping("/students/{studentId}")
	public ResponseEntity retrieveCoursesForStudent(@PathVariable String studentId) {
		try {
			logger.info("retrieveCoursesForStudent");
			return new ResponseEntity(studentService.retrieveStudent(studentId), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("cannot retrieve Courses For Student");
			return new ResponseEntity("cannot retrieve Courses For Student", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/courses/{courseId}")
	public ResponseEntity retrieveDetailsForCourse(@PathVariable String courseId) {
		try {
			logger.info("retrieveDetailsForCourse");
			return new ResponseEntity(studentService.retrieveCourse(courseId), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("cannot retrieve Courses For Student");
			return new ResponseEntity("retrieve Details ForCourse", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PostMapping("/students/addcourses")
	public ResponseEntity addCourse(@RequestBody Course newCourse) {
		try {
			logger.info("addCourse");
			Course addCourse = studentService.addCourse(newCourse);
			return new ResponseEntity(addCourse, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity("cannot add Course", HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("/students/getAllcourses")
	public ResponseEntity getAllCourse() {
		try {
			logger.info("getAllCourse");
			List<Course> allCourse = studentService.getAllCourse();
			return new ResponseEntity(allCourse, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity("cannot getAllCourse", HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@PostMapping("/students/addstudent")
	public ResponseEntity addStudent(@RequestBody Student student) {
		try {
			logger.info("addStudent");
			Student addedStudent = studentService.addStudent(student);
			return new ResponseEntity(addedStudent, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity("cannot add Student", HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@GetMapping("/students/getAllstudent")
	public ResponseEntity getAllStudent() {
		try {
			logger.info("getAllStudent");
			List<Student> allStudent = studentService.getAllStudent();
			return new ResponseEntity(allStudent, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity("cannot getAllStudent", HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@DeleteMapping("/students/{studentId}/delete")
	public ResponseEntity deleteStudent(@PathVariable String studentId) {

		try {
			logger.info("deleteStudent");
			studentService.deleteStudent(studentId);
			return new ResponseEntity("deleted Student", HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity("cannot delete Student", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/courses/{courseId}/delete")
	public ResponseEntity deleteCourse(@PathVariable String courseId) {

		try {
			logger.info("deleteCourse");
			studentService.deleteCourse(courseId);
			return new ResponseEntity("deleted Course", HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity("cannot delete Course", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/students/{studentId}/addstudent")
	public ResponseEntity addCourseToExistingStudent(@PathVariable String studentId, @RequestBody Course newCourse) {
		try {
			logger.info("addCourseToExistingStudent");
			Student addedStudent = studentService.addCourseToExistingStudent(studentId, newCourse);
			return new ResponseEntity(addedStudent, HttpStatus.OK);
		} catch (CustomException e) {
			return new ResponseEntity("cannot addCourseToExistingStudent", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/students/books")
	public ResponseEntity books(@RequestBody Userdata userdata ) throws CustomException {

		restTemplate.basicAuthentication(userdata.getUsename(), userdata.getPawword());
		Book restbook = restTemplate.build().getForObject("http://localhost:8070/rest/books", Book.class);
		return new ResponseEntity(restbook, HttpStatus.OK);

}
}
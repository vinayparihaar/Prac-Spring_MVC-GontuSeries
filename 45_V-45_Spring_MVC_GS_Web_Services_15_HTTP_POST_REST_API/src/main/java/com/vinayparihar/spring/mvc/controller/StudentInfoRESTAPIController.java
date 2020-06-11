/**
 * 
 */
package com.vinayparihar.spring.mvc.controller;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author VinayParihar
 * @date May 30, 2020 @time 3:53:39 PM
 */

@RestController
public class StudentInfoRESTAPIController {
	// @RequestMapping(value = "/students", method = RequestMethod.GET, produces =
	// MediaType.APPLICATION_XML_VALUE)
	@RequestMapping(value = "/students", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Student> getStudentsList() {

		Student student1 = new Student();
		student1.setStudentName("Aadhya Parihar");

		Student student2 = new Student();
		student2.setStudentName("Vicky Parihar");

		Student student3 = new Student();
		student3.setStudentName("Laxmi Parihar");

		Student student4 = new Student();
		student4.setStudentName("Susheela Parihar");

		ArrayList<Student> listOfStudents = new ArrayList<Student>();
		listOfStudents.add(student1);
		listOfStudents.add(student2);
		listOfStudents.add(student3);
		listOfStudents.add(student4);

		return listOfStudents;

	}

	// @RequestMapping(value = "/students/{name}", method =
	// RequestMethod.GET,produces = MediaType.APPLICATION_XML_VALUE)
	@RequestMapping(value = "/students/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Student getStudent(@PathVariable("name") String studentName) {
		Student student = new Student();
		student.setStudentName(studentName);
		student.setStudentHobby("Music");
		return student;

	}

	// @RequestMapping(value = "/students/{name}", method =
	// RequestMethod.PUT,consumes = MediaType.APPLICATION_XML_VALUE)
	@RequestMapping(value = "/students/{name}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> updateStudent(@PathVariable("name") String studentName, @RequestBody Student student) {

		// finds the matching student record using "studentName" from DB.
		// update the matching student record with the information of the student sent b
		// y the client.
		System.out.println("********** Student details before Updation **********");
		System.out.println("Student Name : " + studentName);
		System.out.println("********** Student details after Updation **********");
		System.out.println("Student Name : " + student.getStudentName());
		System.out.println("Student Hobby : " + student.getStudentHobby());
		
		HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.add("key1", "value1");
		httpHeaders.add("key2", "value2");
		httpHeaders.add("key3", "value3");

		// return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
		return new ResponseEntity<Boolean>(true,httpHeaders,HttpStatus.OK);
	}
	
	//********************** Creating/Posting Student record **********************
	@RequestMapping(value = "/students", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> createStudent(@RequestBody Student student) {
		
		//inserting Student record into database
		System.out.println("Created Students record : ");
		System.out.println("Student Name : " + student.getStudentName());
		System.out.println("Student Hobby : " + student.getStudentHobby());
		
		HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.add("Location", ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}").buildAndExpand(student.getStudentName()).toUri().toString());
		
		return new ResponseEntity<Boolean>(true,httpHeaders, HttpStatus.CREATED);
		
	}

}

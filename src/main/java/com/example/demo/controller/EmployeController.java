package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.EmployeDto;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Employe;
import com.example.demo.service.EmployeService;

@RestController
public class EmployeController {

	@Autowired
	private EmployeService employeService;
		
	// GET http://localhost:8080/add/employe?firstName=Wick&lastName=John	
	@RequestMapping(value = "/add/employe", method = RequestMethod.GET)
	public ResponseEntity<Employe> addEmploye(@RequestParam("firstName") String firstName, 
			@RequestParam("lastName") String lastName) {
		Employe e = new Employe(firstName, lastName);
		employeService.createEmploye(e);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	// GET http://localhost:8080/remove/employe?empId=1
	@RequestMapping(value = "/remove/employe", method = RequestMethod.GET)
	public ResponseEntity<String> removeEmploye(@RequestParam("empId") int empId) {				
		employeService.deleteEmploye(empId);
		return new ResponseEntity<>("Employe Deleted", HttpStatus.OK);
	}
	
	// GET http://localhost:8080/all/employe
	@RequestMapping(value = "/all/employe", method = RequestMethod.GET)
	public ResponseEntity<List<Employe>> fetchAllEmployes(){
		return new ResponseEntity<>(employeService.getAllEmployes(), HttpStatus.OK);
	}
	
	// GET http://localhost:8080/all/employe/1
	@RequestMapping(value = "/all/employe/{empId}", method = RequestMethod.GET)
	public ResponseEntity<Employe> getEmploye(@PathVariable("empId") int empId){			
		Employe employe = employeService.getEmploye2(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employe not found :: " + empId));		
		return new ResponseEntity<>(employe, HttpStatus.OK);
	}
	
	// DELETE http://localhost:8080/delete/employe/1
	@RequestMapping(value = "/delete/employe/{empId}", method = RequestMethod.DELETE)
	public Map<String, Boolean> deleteEmploye(@PathVariable("empId") int empId) {
		Employe employe = employeService.getEmploye2(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employe not found :: " + empId));		
		employeService.deleteEmploye(employe.getEmpId());
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);		
		return response;
	}
	
	// POST http://localhost:8080/add/employe
	@RequestMapping(value = "/add/employe", method = RequestMethod.POST)
	@Transactional
	public ResponseEntity<Employe> addEmploye(@Valid @RequestBody EmployeDto employeDto) {
		Employe e = new Employe();
		e.setFirstName(employeDto.getFirstName());
		e.setLastName(employeDto.getLastName());
		employeService.saveOrUpdate(e);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	
	// PUT http://localhost:8080/edit/employe/1
	@RequestMapping(value = "/edit/employe/{empId}", method = RequestMethod.PUT)
	public ResponseEntity<?> editEmploye(@Valid @PathVariable("empId") int empId,
			@Valid @RequestBody() EmployeDto employeDto) {
		Employe employeToUpdate = employeService.getEmploye2(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employe not found :: " + empId));	
		employeToUpdate.setFirstName(employeDto.getFirstName());
		employeToUpdate.setLastName(employeDto.getLastName());	
		employeService.saveOrUpdate(employeToUpdate);
		return new ResponseEntity<>(employeToUpdate, HttpStatus.OK);
	}
	
	
}

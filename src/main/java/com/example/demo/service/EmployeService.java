package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employe;
import com.example.demo.repository.EmployeRepository;

@Service
public class EmployeService {

	@Autowired
	private EmployeRepository employeRepository;
	
	public Employe createEmploye(Employe employe) {
		Employe e = new Employe();
        e.setFirstName(employe.getFirstName());
        e.setLastName(employe.getLastName());
		employeRepository.save(employe);
		return e;
	}
	
	@Transactional()
	public void deleteEmploye(int empId) {
		employeRepository.deleteById(empId);
	}
	
	public List<Employe> getAllEmployes(){
        return employeRepository.findAll();
    }
	
	public Employe getEmploye(int id){
        return employeRepository.findById(id).get();
    }
	
	public Optional<Employe> getEmploye2(int id){
        return employeRepository.findById(id);
    }
	
	@Transactional()
	public Employe saveOrUpdate(Employe employe) {
		return employeRepository.save(employe);
	}

}

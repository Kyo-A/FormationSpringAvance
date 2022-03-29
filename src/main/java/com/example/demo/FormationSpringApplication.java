package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.example.demo.model.Employe;
import com.example.demo.repository.EmployeRepository;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class FormationSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormationSpringApplication.class, args);
	}
	
//	@Bean
//	public CommandLineRunner demo(EmployeRepository employeRepository) {
//		return (args) ->{		
//			employeRepository.save(new Employe("NOM1", "PRENOM1"));
//			employeRepository.save(new Employe("NOM2", "PRENOM2"));
//			employeRepository.save(new Employe("NOM3", "PRENOM3"));
//		};
//	}
}

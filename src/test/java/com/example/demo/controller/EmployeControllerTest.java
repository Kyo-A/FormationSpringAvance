package com.example.demo.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.demo.model.Employe;
import com.example.demo.service.EmployeService;

@WebMvcTest(EmployeController.class)
public class EmployeControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	EmployeService employeService;
	
	Employe e1 = new Employe(1, "John", "Wick");
	Employe e2 = new Employe(2, "Pat", "Dupont");
	Employe e3 = new Employe(3, "Elon", "Musk");
	
	@Test
	public void getAllEmployes_success() throws Exception{
		List<Employe> employes = new ArrayList<Employe>(Arrays.asList(e1, e2, e3));
		
		Mockito.when(employeService.getAllEmployes()).thenReturn(employes);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/all/employe").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", hasSize(3)))
					.andExpect(jsonPath("$[0].firstName", is("John")));		
	}
	
	@Test
	public void getEmployeById_success() throws Exception{

		Mockito.when(employeService.getEmploye2(e1.getEmpId())).thenReturn(Optional.of(e1));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/all/employe/1").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$", notNullValue()))
					.andExpect(jsonPath("$.lastName", is("Wick")));		
	}
	
	@Test
	public void deleteEmployeById_success() throws Exception{

		Mockito.when(employeService.getEmploye2(e1.getEmpId())).thenReturn(Optional.of(e1));
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/delete/employe/1").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());		
	}
	
	@Test
	public void createEmploye_success() throws Exception{
		
		Employe e = new Employe(4, "Bob", "Marley");

		Mockito.when(employeService.saveOrUpdate(e)).thenReturn(e);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/add/employe")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(e));

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.lastName", is("Marley")));
	}
	
	@Test
	public void editEmploye_success() throws Exception {
		Employe e = new Employe(1, "John", "Travolta");
		
		Mockito.when(employeService.getEmploye2(e1.getEmpId())).thenReturn(Optional.of(e1));
		Mockito.when(employeService.saveOrUpdate(e)).thenReturn(e);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/edit/employe/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(e));

		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.firstName", is("John")));
	}


}

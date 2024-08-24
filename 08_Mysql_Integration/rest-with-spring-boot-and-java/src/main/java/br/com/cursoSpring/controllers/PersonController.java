package br.com.cursoSpring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoSpring.model.Person;
import br.com.cursoSpring.services.PersonServices;

@RestController
//@RequestMapping("/person")

public class PersonController {
	
	@Autowired
	
	private PersonServices service;

	@GetMapping("/person")
	public List<Person> findAll(){
		return service.findAll();
	}

	@GetMapping("/person/{id}")
	public Person findById(@PathVariable Long id) throws Exception{
		return service.findById(id);
	}

	@PostMapping("/person")
	public Person create(@RequestBody Person person) throws Exception{
		return service.create(person);
	}
	
	@PutMapping("/person")
	public Person update(@RequestBody Person person) throws Exception{
		return service.update(person);
	}
	
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws Exception{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}

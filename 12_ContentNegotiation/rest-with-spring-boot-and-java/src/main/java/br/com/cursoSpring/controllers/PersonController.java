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
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoSpring.data.vo.v1.PersonVO;
import br.com.cursoSpring.data.vo.v2.PersonVOV2;
import br.com.cursoSpring.services.PersonServices;

@RestController

public class PersonController {
	
	@Autowired
	
	private PersonServices service;

	@GetMapping("/person")
	public List<PersonVO> findAll(){
		return service.findAll();
	}

	@GetMapping("/person/{id}")
	public PersonVO findById(@PathVariable Long id) throws Exception{
		return service.findById(id);
	}

	@PostMapping("/person")
	public PersonVO create(@RequestBody PersonVO PersonVO) throws Exception{
		return service.create(PersonVO);
	}
	
	@PostMapping("/person/v2")
	public PersonVOV2 createV2(@RequestBody PersonVOV2 PersonVOV2) throws Exception{
		return service.createV2(PersonVOV2);
	}
	
	@PutMapping("/person")
	public PersonVO update(@RequestBody PersonVO PersonVO) throws Exception{
		return service.update(PersonVO);
	}
	
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws Exception{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}

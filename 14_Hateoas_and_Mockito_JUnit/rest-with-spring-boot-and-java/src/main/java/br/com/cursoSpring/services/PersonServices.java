package br.com.cursoSpring.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.cursoSpring.controllers.PersonController;
import br.com.cursoSpring.data.vo.v1.PersonVO;
import br.com.cursoSpring.data.vo.v2.PersonVOV2;
import br.com.cursoSpring.exceptions.RequiredObjectIsNullException;
import br.com.cursoSpring.exceptions.ResourceNotFoundException;
import br.com.cursoSpring.mapper.DozerMapper;
import br.com.cursoSpring.mapper.custom.PersonMapper;
import br.com.cursoSpring.model.Person;
import br.com.cursoSpring.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;
	
	public List<PersonVO> findAll(){
		
		logger.info("Finding all people");
		
		var persons =  DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		persons.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		return persons;
	} 
	
	public PersonVO findById(Long id) throws Exception{
		
		logger.info("Finding one Person!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public PersonVO create(PersonVO person) throws Exception {
		
		if (person == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one Person!");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}

	public PersonVOV2 createV2(PersonVOV2 person) {
		
		logger.info("Creating one Person with v2!");
		
		var entity = mapper.convertVoToEntity(person);
		var vo = mapper.convertEntityToVo(repository.save(entity));
		
		return vo;
	}

	public PersonVO update(PersonVO person) throws Exception {
		
		if (person == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating one Person!");
		
		var entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));;
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		entity.setAddress(person.getAddress());
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		
		return vo;
	}
	

	public void delete(Long id) {
		
		logger.info("deleting one PersonVO!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));;
		
		repository.delete(entity);
	}
}

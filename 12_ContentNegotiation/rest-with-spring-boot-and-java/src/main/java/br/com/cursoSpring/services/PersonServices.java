package br.com.cursoSpring.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoSpring.data.vo.v1.PersonVO;
import br.com.cursoSpring.data.vo.v2.PersonVOV2;
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
		
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	} 
	
	public PersonVO findById(Long id) {
		
		logger.info("Finding one Person!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		
		logger.info("Creating one Person!");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;
	}

	public PersonVOV2 createV2(PersonVOV2 person) {
		
		logger.info("Creating one Person with v2!");
		
		var entity = mapper.convertVoToEntity(person);
		var vo = mapper.convertEntityToVo(repository.save(entity));
		
		return vo;
	}

	public PersonVO update(PersonVO person) {
		
		logger.info("Updating one Person!");
		
		var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));;
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		entity.setAddress(person.getAddress());
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;
	}
	

	public void delete(Long id) {
		
		logger.info("deleting one PersonVO!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));;
		
		repository.delete(entity);
	}
}

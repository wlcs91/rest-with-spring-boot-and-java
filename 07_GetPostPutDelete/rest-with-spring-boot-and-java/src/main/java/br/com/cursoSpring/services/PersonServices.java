package br.com.cursoSpring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.cursoSpring.model.Person;

@Service
public class PersonServices {

	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public List<Person> findAll(){
		
		logger.info("Finding all people");
		
		List<Person> persons = new ArrayList<>();
		for(int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		
		return persons;
	} 
	
	public Person findById(String id) {
		
		logger.info("Finding one person!");
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Washington");
		person.setLastName("Silva");
		person.setGender("Male");
		person.setAdress("Arthur Lundgren 1 - Paulista");
		
		return person;
	}
	
	private Person mockPerson(int i) {
		
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("First Name " + i);
		person.setLastName("Last Name " + i);
		person.setGender("Male");
		person.setAdress("Some adress in Brazil");
		
		return person;
	}
	
	public Person create(Person person) {
		
		logger.info("Creating one person!");
		return person;
	}
	

	public Person update(Person person) {
		
		logger.info("Updating one person!");
		return person;
	}
	

	public void delete(String id) {
		logger.info("deleting one person!");
	}
}

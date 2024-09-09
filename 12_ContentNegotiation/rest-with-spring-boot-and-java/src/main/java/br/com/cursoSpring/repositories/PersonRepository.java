package br.com.cursoSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cursoSpring.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}

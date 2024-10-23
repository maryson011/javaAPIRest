package com.api.rest.services;

import com.api.rest.exceptions.ResourceNotFoundException;
import com.api.rest.model.Person;
import com.api.rest.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    @Autowired
    PersonRepository personRepository;

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person create(Person person) {
        logger.info("Creating one person");
        return personRepository.save(person);
    }
    public Person update(Person person) {
        logger.info("Updating one person");
        var entity = personRepository.findById(person.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFistName(person.getFistName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return personRepository.save(person);
    }

    public void delete(Long id) {
        logger.info("Deleting one person");
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        personRepository.delete(entity);
    }

    public List<Person> findAll() {
        logger.info("Finding all person!");
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        logger.info("Finding one person!");
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }
}

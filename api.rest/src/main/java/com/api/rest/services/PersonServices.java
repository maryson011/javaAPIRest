package com.api.rest.services;

import com.api.rest.exceptions.ResourceNotFoundException;
import com.api.rest.model.Person;
import com.api.rest.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    @Autowired
    PersonRepository personRepository;

//    private final AtomicLong counter = new AtomicLong();
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
//        List<Person> persons = new ArrayList<>();
//        for (int i = 0; i < 8; i++) {
//            Person person = mockPerson(i);
//            persons.add(person);
//        }
//        return persons;
        return personRepository.findAll();
    }

//    private Person mockPerson(int i) {
//        Person person = new Person();
//        person.setId(counter.incrementAndGet());
//        person.setFistName("Person name " + i);
//        person.setLastName("Last name " + i);
//        person.setAddress("Some address in Brasil " + i);
//        person.setGender(i % 2 == 0 ? "Male" : "Female");
//        return person;
//    }


    public Person findById(Long id) {

        logger.info("Finding one person!");
//        Person person = new Person();
//        person.setId(counter.incrementAndGet());
//        person.setFistName("Maryson");
//        person.setLastName("Silva");
//        person.setAddress("Varzea");
//        person.setGender("male");
//        return person;
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }
}

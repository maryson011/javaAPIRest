package com.api.rest.services;

import com.api.rest.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person create(Person person) {
        logger.info("Creating one person");
        return person;
    }
    public Person update(Person person) {
        logger.info("Updating one person");
        return person;
    }

    public void delete(String id) {
        logger.info("Deleting one person");
    }

    public List<Person> findAll() {

        logger.info("Finding all person!");
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFistName("Person name " + i);
        person.setLastName("Last name " + i);
        person.setAddress("Some address in Brasil " + i);
        person.setGender(i % 2 == 0 ? "Male" : "Female");
        return person;
    }


    public Person findById(String id) {

        logger.info("Finding one person!");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFistName("Maryson");
        person.setLastName("Silva");
        person.setAddress("Varzea");
        person.setGender("male");
        return person;
    }
}

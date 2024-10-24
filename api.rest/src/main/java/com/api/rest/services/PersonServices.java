package com.api.rest.services;

import com.api.rest.data.vo.v1.PersonVO;
import com.api.rest.data.vo.v2.PersonVOV2;
import com.api.rest.exceptions.ResourceNotFoundException;
import com.api.rest.mapper.DozerMapper;
import com.api.rest.mapper.custom.PersonMapper;
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

    @Autowired
    PersonMapper mapper;

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public PersonVO create(PersonVO person) {
        logger.info("Creating one person");
        var entity = DozerMapper.parseObject(person, Person.class);
        return DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        logger.info("Creating one person with V2");
        var entity = mapper.convertVOToEntity(person);
        return mapper.convertEntityToVO(personRepository.save(entity));
    }

    public PersonVO update(PersonVO person) {
        logger.info("Updating one person");
        var entity = personRepository.findById(person.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFistName(person.getFistName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one person");
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        personRepository.delete(entity);
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all person!");
        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {
        logger.info("Finding one person!");
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }
}

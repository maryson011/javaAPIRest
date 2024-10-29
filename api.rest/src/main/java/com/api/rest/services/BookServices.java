package com.api.rest.services;

import com.api.rest.controller.BookController;
import com.api.rest.controller.PersonController;
import com.api.rest.data.vo.v1.BookVO;
import com.api.rest.data.vo.v1.PersonVO;
import com.api.rest.data.vo.v2.PersonVOV2;
import com.api.rest.exceptions.ResourceNotFoundException;
import com.api.rest.mapper.DozerMapper;
import com.api.rest.mapper.custom.PersonMapper;
import com.api.rest.model.Book;
import com.api.rest.model.Person;
import com.api.rest.repositories.BookRepository;
import com.api.rest.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    @Autowired
    BookRepository bookRepository;

    private Logger logger = Logger.getLogger(BookServices.class.getName());

    public BookVO create(BookVO book) {
        logger.info("Creating one person");
        var entity = DozerMapper.parseObject(book, Book.class);
        var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
        BookVO vo1 = new BookVO();
        vo1.setKey(entity.getId());
        vo1.setAuthor(entity.getAuthor());
        vo1.setTitle(entity.getTitle());
        vo1.setLaunchDate(entity.getLaunchDate());
        vo1.setPrice(entity.getPrice());
        try {
            vo1.add(linkTo(methodOn(BookController.class).findById(vo1.getKey())).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vo1;
    }

    public BookVO update(BookVO book) {
        logger.info("Updating one book");
        var entity = bookRepository.findById(book.getKey())
                        .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setAuthor(book.getAuthor());
        entity.setTitle(book.getTitle());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        return DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one book");
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        bookRepository.delete(entity);
    }

    public List<BookVO> findAll() {
        logger.info("Finding all book!");
//        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
        List<Book> entity = bookRepository.findAll();
        List<BookVO> entityReturn = new ArrayList<>();
        for (Book en: entity) {
            BookVO vo1 = new BookVO();
            vo1.setKey(en.getId());
            vo1.setAuthor(en.getAuthor());
            vo1.setTitle(en.getTitle());
            vo1.setLaunchDate(en.getLaunchDate());
            vo1.setPrice(en.getPrice());
            try {
                vo1.add(linkTo(methodOn(BookController.class).findById(en.getId())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            entityReturn.add(vo1);
        }

        return entityReturn;
    }

    public BookVO findById(Long id) {
        logger.info("Finding one book!");
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
        BookVO vo1 = new BookVO();
        vo1.setKey(entity.getId());
        vo1.setAuthor(entity.getAuthor());
        vo1.setTitle(entity.getTitle());
        vo1.setLaunchDate(entity.getLaunchDate());
        vo1.setPrice(entity.getPrice());
        try {
            vo1.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vo1;
    }
}

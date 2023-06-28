package ru.alishev.springcourse.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.repositories.BookRepository;
import ru.alishev.springcourse.repositories.PeopleRepository;

@Service
@Transactional(readOnly = true)
public class PeopleService {
	private final PeopleRepository peopleRepository;
	private final BookRepository bookRepository;
	
	@Autowired
	public PeopleService(PeopleRepository peopleRepository, BookRepository bookRepository) {
		super();
		this.peopleRepository = peopleRepository;
		this.bookRepository = bookRepository;
	}
	
	public List<Person> findAll() {
		return peopleRepository.findAll();
	}
	
	public Person findOne(int id) {
		Optional <Person> foundPerson = peopleRepository.findById(id);
		return foundPerson.orElse(null);
	}
	
	@Transactional
	public void save(Person person) {
		peopleRepository.save(person);
	}
	
	@Transactional
	public void update (int id, Person updatedPerson) {
		updatedPerson.setId(id);
		peopleRepository.save(updatedPerson);
	}
	
	@Transactional
	public void delete (int id) {
		peopleRepository.deleteById(id);
	}

	/*
	public List<Book> queryById(int id) {
		
		// return peopleRepository.findById(personId).map(person -> person.getBooks()).orElse(null);
		return bookRepository.queryById(id);
	}
	 */
}

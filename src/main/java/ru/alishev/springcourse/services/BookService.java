package ru.alishev.springcourse.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.repositories.BookRepository;

@Service
@Transactional(readOnly = true)
public class BookService {
	private final BookRepository bookRepository;
	
	@Autowired
	public BookService(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}
	
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
	
	public List <Book> findAll(Pageable pageable) {
		return bookRepository.findAll(pageable).getContent();
	}
	
	public List <Book> findAll(Sort var1) {
		return bookRepository.findAll(var1);
	}
	/*
	public List <Book> findAll(Pageable pageable, Sort var1) {
		return bookRepository.findAll(pageable).getContent();
	}
	*/
	public Book findOne(int id) {
		Optional<Book> foundBook = bookRepository.findById(id);
		return foundBook.orElse(null);
	}
	
	
	@Transactional	public void save(Book book) {
		bookRepository.save(book);
	}
	
	@Transactional
	public void update(int id, Book updatedBook) {
		updatedBook.setId(id);
		bookRepository.save(updatedBook);
	}
	
	@Transactional
	public void delete(int id) {
		bookRepository.deleteById(id);
	}
	
	public Person getBookOwner(int id) {
		return bookRepository.findById(id).map(Book::getOwner).orElse(null);
	}
	
	@Transactional
	public void release(int id) {
		bookRepository.findById(id).ifPresent(book-> { 
			book.setOwner(null);
		});
	}
	
	@Transactional
	public void takeBook(int id, Person person) {
		bookRepository.findById(id).ifPresent(book -> book.setOwner(person));
	}
	
	
	public List<Book> findAllByOwnerId(int owner_id) {
		if(bookRepository.findAllByOwnerId(owner_id).isEmpty()) {
			return null;
		}
		return bookRepository.findAllByOwnerId(owner_id);
		}
	
	public List <Book> findByNameStartingWith(String name) {
		return bookRepository.findByNameStartingWith(name);
	}
}

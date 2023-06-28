package ru.alishev.springcourse.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	List <Book> findAllByOwnerId(int owner_id);
	Page <Book> findAll(Pageable pageable);
	List <Book> findAll(Sort var1);
	List <Book> findByNameStartingWith(String name);
}

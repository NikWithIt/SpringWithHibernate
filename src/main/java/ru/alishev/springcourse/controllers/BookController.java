package ru.alishev.springcourse.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;
import ru.alishev.springcourse.services.BookService;
import ru.alishev.springcourse.services.PeopleService;
//import ru.alishev.springcourse.util.BookValidator;

@Controller
@RequestMapping("/books")
public class BookController {
	BookService bookService; 
	PeopleService peopleService;
	//BookValidator bookValidator;
		
	//insert into constructor BookValidator
	@Autowired
	public BookController(BookService bookService, PeopleService peopleService) {
		super();
		this.bookService = bookService;
		this.peopleService = peopleService;
	}

	@GetMapping()
	public String indexPage(Model model, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer booksPerPage,
			@RequestParam(required = false) boolean sortByYear) {
		if ((page == null || booksPerPage == null) && sortByYear == true) {
			model.addAttribute("books", bookService.findAll(Sort.by("yearOfProduction")));
		}else if ((page == null || booksPerPage == null) && sortByYear == true) {
			model.addAttribute("books", bookService.findAll(PageRequest.of(page, booksPerPage)));
		} else if ((page == null || booksPerPage == null) && sortByYear == false){
			model.addAttribute("books", bookService.findAll());
		} else {
			model.addAttribute("books", bookService.findAll(PageRequest.of(page, booksPerPage, Sort.by("yearOfProduction"))));
		}
		return "books/index";
	}

/*	
	@GetMapping()
	public String indexPage(Model model, @RequestParam(required = false) boolean sortByYear) {
		if (sortByYear == false) {
			model.addAttribute("books", bookService.findAll());
		} else {
			model.addAttribute("books", bookService.findAll(Sort.by("yearOfProduction")));
		}
		return "books/index";
	}
*/
	
	
	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person) {
		model.addAttribute("book", bookService.findOne(id));
		if (bookService.getBookOwner(id)!=null) {
			model.addAttribute("person_with_book", bookService.getBookOwner(id));
		} else {
		model.addAttribute("people", peopleService.findAll());
		}
		return "books/show";
	}
	
	@GetMapping("/new")
	public String newBook(Model model) {
		model.addAttribute("book", new Book());
		return "books/new";
	}
	
	@PostMapping()
	public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		/*
		bookValidator.validate(book, bindingResult);
		if (bindingResult.hasErrors()) {
			return "/books/new";
		}
		*/
		bookService.save(book);
		return "redirect:/books";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("book", bookService.findOne(id));
		return "/books/edit";
	}
	
	@PatchMapping("{id}")
	public String update(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
		bookService.update(id, book);
		return "redirect:/books";
	}
	
	@PatchMapping("/{id}/return")
	public String returnBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
		bookService.release(id);
		return "redirect:/books/{id}";
	}
	
	@PatchMapping("{id}/take_book")
	public String takeBook(@PathVariable("id") int bookId, Person person,
			  @ModelAttribute("book") Book book, Model model) {
		model.addAttribute("person", model);
		bookService.takeBook(bookId, person);
		System.out.println(book.getReservedAt());
		return "redirect:/books/{id}";
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") int id) {
		bookService.delete(id);
		return "redirect:/books";
	}
	
	@GetMapping("/search")
    public String searchPage() {
        return "books/search";
    }

	@PostMapping("/search")
	public String search(Model model, @RequestParam String query) {
		model.addAttribute("books", bookService.findByNameStartingWith(query));
		return "books/search";
	}
	
}

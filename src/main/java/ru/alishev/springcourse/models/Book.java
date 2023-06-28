package ru.alishev.springcourse.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Book")
public class Book {
	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	@NotEmpty(message="Name shouldn't be empty")
	@Size(min=1, max=256, message="Name size must be between 1 and 256")
	@Column(name = "name")
	private String name;
	
	@NotNull(message="Author shouldn't be empty")
	@Column(name = "author")
	private String author;
	
	@Column(name = "year_of_production")
	private int yearOfProduction;
	
	@ManyToOne
	@JoinColumn(name = "owner_id", referencedColumnName = "id")
	private Person owner;
	
	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	public Book() {
		super();
	}
	
	public Book(String name, String author, int yearOfProduction) {
		super();
		this.name = name;
		this.author = author;
		this.yearOfProduction = yearOfProduction;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(int yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}
}

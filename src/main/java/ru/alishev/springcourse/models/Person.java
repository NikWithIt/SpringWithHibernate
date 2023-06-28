package ru.alishev.springcourse.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



/**
 * @author Neil Alishev
 */

@Entity
@Table(name="Person")
public class Person {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @NotEmpty(message="name shouldn't be empty")
    @Size(min=1, max=30, message="Name should be betweeb 1 and 30 characters")
    @Column(name = "name")
    private String name;

    @Min(value=0, message="Enter valid date")
    @Max(value=150,message="Enter valid date")
    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "owner")
    public List<Book> books;
    
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Person() {

    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
   
}
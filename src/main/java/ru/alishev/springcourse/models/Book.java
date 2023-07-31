package ru.alishev.springcourse.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Temporal;


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
	
	@Column(name = "reserved_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reservedAt;
	
	@Transient
	boolean isExpired;
	
	public boolean getIsExpired() {
		long tenDays = 10 * 24 * 60 * 60 * 1000;
		Date currentDate = new Date();
		if (currentDate.getTime() - this.getReservedAt().getTime() < tenDays) {
			return false;
		} else {
			return true;
		}
	}

	public void setIsExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	public Date getReservedAt() {
		return reservedAt;
	}

	public void setReservedAt(Date reservedAt) {
		this.reservedAt = reservedAt;
	}

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

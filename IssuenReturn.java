package com.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class IssuenReturn {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String loandate;
	private String returndate;
	
	@ManyToOne
	@JoinColumn(name = "books_id")
	private Books book;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private Customer customer;

	public IssuenReturn() {
		super();
		
	}

	public IssuenReturn(int id, String loandate, String returndate, Books book, Customer customer) {
		super();
		this.id = id;
		this.loandate = loandate;
		this.returndate = returndate;
		this.book = book;
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoandate() {
		return loandate;
	}

	public void setLoandate(String loandate) {
		this.loandate = loandate;
	}

	public String getReturndate() {
		return returndate;
	}

	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}

	public Books getBook() {
		return book;
	}

	public void setBook(Books book) {
		this.book = book;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

}

package com.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;
	private String cname;
	private String caddress;
	private long cphone_no;
	private int no_of_book;
	
	public Customer() {
		super();
		
	}

	public Customer(int cid, String cname, String caddress, long cphone_no, int no_of_book) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.caddress = caddress;
		this.cphone_no = cphone_no;
		this.no_of_book = no_of_book;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCaddress() {
		return caddress;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}

	public long getCphone_no() {
		return cphone_no;
	}

	public void setCphone_no(long cphone_no) {
		this.cphone_no = cphone_no;
	}

	public int getNo_of_book() {
		return no_of_book;
	}

	public void setNo_of_book(int no_of_book) {
		this.no_of_book = no_of_book;
	}
	
	

}

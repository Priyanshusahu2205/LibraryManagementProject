package com.project;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class LibrarySystem {
	
	public void create() {
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session s = sf.openSession();
		Transaction t = s.beginTransaction();
		
		Books b1 = new Books();
		b1.setBid(2205); b1.setBtitle("The Art Of Being Alone"); b1.setBprice(500.0);
		
		Books b2 = new Books();
		b2.setBid(1204); b2.setBtitle("This is Going to Hurt"); b2.setBprice(1200.0);
		
		Books b3 = new Books();
		b3.setBid(2203); b3.setBtitle("Absolute Power"); b3.setBprice(800.0);
		
		Books b4 = new Books();
		b4.setBid(12890); b4.setBtitle("The Alchemist"); b4.setBprice(400.0);
		
		Customer c1 = new Customer();
		c1.setCid(10382); c1.setCname("Rohit"); c1.setCphone_no(829735370);c1.setCaddress("Pune"); c1.setNo_of_book(2);
		
		Customer c2 = new Customer();
		c2.setCid(23464); c2.setCname("Rohan"); c2.setCphone_no(819534226);c2.setCaddress("Banglore"); c2.setNo_of_book(3);
		
		Customer c3 = new Customer();
		c3.setCid(24346); c3.setCname("Harsh"); c3.setCphone_no(922565379);c3.setCaddress("Hyderabad"); c3.setNo_of_book(1);
		
		Customer c4 = new Customer();
		c4.setCid(56346); c4.setCname("Pihu"); c4.setCphone_no(728565379);c4.setCaddress("Bhopal"); c4.setNo_of_book(2);
		
		
		IssuenReturn ir = new IssuenReturn();
		ir.setId(4); ir.setBook(b1); ir.setBook(b2); ir.setBook(b3); ir.setBook(b4);
		
		ir.setCustomer(c1); ir.setCustomer(c2); ir.setCustomer(c3); ir.setCustomer(c4);
		ir.setLoandate("2024-11-04");ir.setReturndate("2024-11-10");
		
		s.save(b1); s.save(b2); s.save(b3); s.save(b4);
		s.save(c1); s.save(c2); s.save(c3); s.save(c4);
		s.save(ir);
		
		t.commit();
		s.close();
		
	}
	
	public void update(){
		  
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		Session s = sf.openSession();
		
		Transaction t = s.beginTransaction();
		
		String hql = "update Books set btitle = :a where bid = :k";
		Query q = s.createQuery(hql);
		q.setParameter("a", "Be As You Are"); 
		q.setParameter("k", 4); 
		
		int row = q.executeUpdate();
		System.out.println(row+ " Object is updated"); 
		
		t.commit();
		s.close();
		   
	}
	
	public void read_data(){

	      	Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			
			SessionFactory sf = cfg.buildSessionFactory();
			Session s = sf.openSession();
			
			Transaction t = s.beginTransaction();
			
			String hql = "from Books";
			Query q = s.createQuery(hql);
			List<Books> bk = q.getResultList();
			
			System.out.println("Book Details");
			for(Books b:bk){
				System.out.println(b.getBid());
				System.out.println(b.getBtitle());
				System.out.println(b.getBprice());
			}
			
			t.commit();
			s.close();
	  }
	
	public void delete_data(){

	      	Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			
			SessionFactory sf = cfg.buildSessionFactory();
			Session s = sf.openSession();
			
			Transaction t = s.beginTransaction();
			
			String hql = "delete from Books where Bprice = :p";
			Query q = s.createQuery(hql);
			q.setParameter("p", 800);
			
			
			int del = q.executeUpdate();
			System.out.println(del+ " Object is Deleted"); 
			
			t.commit();
			s.close();
	  }
	
	public static void main(String[] args) {
		
		LibrarySystem ls = new LibrarySystem();
		//ls.create();
		//ls.update();
		//ls.read_data();
		
		ls.delete_data();
		
	}

}

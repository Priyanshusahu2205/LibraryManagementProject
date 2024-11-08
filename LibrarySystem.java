package com.project;


import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class LibrarySystem {
	
	/*public void create() {
		
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
		ls.create();
		//ls.update();
		//ls.read_data();
		
		//ls.delete_data();
		
	}*/
	
	private static Scanner sc = new Scanner(System.in);
    private static SessionFactory sf;

    static {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        sf = cfg.buildSessionFactory();
    }

    public void createBook() {
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        System.out.println("Enter book title:");
        String title = sc.nextLine();
        System.out.println("Enter book price:");
        double price = sc.nextDouble();
        sc.nextLine();

        Books book = new Books();
        book.setBtitle(title);
        book.setBprice(price);

        s.save(book);
        t.commit();
        s.close();
        System.out.println("Book added successfully.");
    }

    public void readBooks() {
        Session s = sf.openSession();
        String hql = "from Books";
        Query q = s.createQuery(hql);
        List<Books> books = q.getResultList();

        System.out.println("Books List:");
        for (Books b : books) {
            System.out.println("ID: " + b.getBid() + ", Title: " + b.getBtitle() + ", Price: " + b.getBprice());
        }
        s.close();
    }

    public void updateBook() {
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        System.out.println("Enter Book ID to update:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter new title:");
        String title = sc.nextLine();

        String hql = "update Books set btitle = :title where bid = :id";
        Query q = s.createQuery(hql);
        q.setParameter("title", title);
        q.setParameter("id", id);

        int rows = q.executeUpdate();
        t.commit();
        s.close();
        System.out.println(rows + " book(s) updated.");
    }

    public void deleteBook() {
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        System.out.println("Enter Book ID to delete:");
        int id = sc.nextInt();

        String hql = "delete from Books where bid = :id";
        Query q = s.createQuery(hql);
        q.setParameter("id", id);

        int rows = q.executeUpdate();
        t.commit();
        s.close();
        System.out.println(rows + " book(s) deleted.");
    }

    public void createCustomer() {
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        System.out.println("Enter customer name:");
        String name = sc.nextLine();
        System.out.println("Enter customer address:");
        String address = sc.nextLine();
        System.out.println("Enter customer phone number:");
        long phone = sc.nextLong();
        System.out.println("Enter number of books borrowed:");
        int numOfBooks = sc.nextInt();
        sc.nextLine();

        Customer customer = new Customer();
        customer.setCname(name);
        customer.setCaddress(address);
        customer.setCphone_no(phone);
        customer.setNo_of_book(numOfBooks);

        s.save(customer);
        t.commit();
        s.close();
        System.out.println("Customer added successfully.");
    }

    public void readCustomers() {
        Session s = sf.openSession();
        String hql = "from Customer";
        Query q = s.createQuery(hql);
        List<Customer> customers = q.getResultList();

        System.out.println("Customers List:");
        for (Customer c : customers) {
            System.out.println("ID: " + c.getCid() + ", Name: " + c.getCname() + ", Address: " + c.getCaddress()
                    + ", Phone: " + c.getCphone_no() + ", Books Borrowed: " + c.getNo_of_book());
        }
        s.close();
    }

    public void updateCustomer() {
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        System.out.println("Enter Customer ID to update details:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter new phone number:");
        long phone = sc.nextLong();
        sc.nextLine();
        System.out.println("Enter new address:");
        String address = sc.nextLine();

        String hql = "update Customer set cphone_no = :phone, caddress = :address where cid = :id";
        Query q = s.createQuery(hql);
        q.setParameter("phone", phone);
        q.setParameter("address", address);
        q.setParameter("id", id);

        int rows = q.executeUpdate();
        t.commit();
        s.close();
        System.out.println(rows + " customer(s) details updated.");
        
    }

    public void deleteCustomer() {
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        System.out.println("Enter Customer ID to delete:");
        int id = sc.nextInt();

        String hql = "delete from Customer where cid = :id";
        Query q = s.createQuery(hql);
        q.setParameter("id", id);

        int rows = q.executeUpdate();
        t.commit();
        s.close();
        System.out.println(rows + " customer(s) deleted.");
    }

    public void createIssuenReturn() {
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        System.out.println("Enter loan date (YYYY-MM-DD):");
        String loanDate = sc.nextLine();
        System.out.println("Enter return date (YYYY-MM-DD):");
        String returnDate = sc.nextLine();

        IssuenReturn ir = new IssuenReturn();
        ir.setLoandate(loanDate);
        ir.setReturndate(returnDate);

        //Fetching existing customer and book
        System.out.println("Enter Customer ID:");
        int cid = sc.nextInt();
        System.out.println("Enter Book ID:");
        int bid = sc.nextInt();
        sc.nextLine();

        Customer customer = s.get(Customer.class, cid);
        Books book = s.get(Books.class, bid);

        if (customer != null && book != null) {
            ir.setCustomer(customer);
            ir.setBook(book);

            s.save(ir);
            t.commit();
            System.out.println("IssuenReturn record added successfully.");
        } else {
            System.out.println("Customer or Book not found!");
        }

        s.close();
    }

    public void readIssuenReturn() {
        Session s = sf.openSession();
        String hql = "from IssuenReturn";
        Query q = s.createQuery(hql);
        List<IssuenReturn> irs = q.getResultList();

        System.out.println("IssuenReturn Records:");
        for (IssuenReturn ir : irs) {
            System.out.println("ID: " + ir.getId() + ", Loan Date: " + ir.getLoandate() +
                    ", Return Date: " + ir.getReturndate() +
                    ", Customer ID: " + ir.getCustomer().getCid() +
                    ", Book ID: " + ir.getBook().getBid());
        }
        s.close();
    }
    
    public void updateIssuenReturn() {
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        System.out.println("Enter IssuenReturn ID to update:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter new loan date (YYYY-MM-DD):");
        String newLoanDate = sc.nextLine();
        System.out.println("Enter new return date (YYYY-MM-DD):");
        String newReturnDate = sc.nextLine();

        String hql = "update IssuenReturn set loandate = :loanDate, returndate = :returnDate where id = :id";
        Query q = s.createQuery(hql);
        q.setParameter("loanDate", newLoanDate);
        q.setParameter("returnDate", newReturnDate);
        q.setParameter("id", id);

        int rows = q.executeUpdate();
        t.commit();
        s.close();
        System.out.println(rows + " IssuenReturn record(s) updated.");
    }
    
    public void deleteIssuenReturn() {
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        System.out.println("Enter IssuenReturn ID to delete:");
        int id = sc.nextInt();
        sc.nextLine();

        String hql = "delete from IssuenReturn where id = :id";
        Query q = s.createQuery(hql);
        q.setParameter("id", id);

        int rows = q.executeUpdate();
        t.commit();
        s.close();
        System.out.println(rows + " IssuenReturn record(s) deleted.");
    }

    public static void main(String[] args) {
        LibrarySystem ls = new LibrarySystem();

        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Books Details:");
            System.out.println("2. Customer Details:");
            System.out.println("3. IssuenReturn Details:");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nBooks Details:");
                    System.out.println("1. Creating a new book details:");
                    System.out.println("2. Read the book details:");
                    System.out.println("3. Update the book details:");
                    System.out.println("4. Delete the details of book:");
                    int booksChoice = sc.nextInt();
                    sc.nextLine(); 
                    switch (booksChoice) {
                        case 1 -> ls.createBook();
                        case 2 -> ls.readBooks();
                        case 3 -> ls.updateBook();
                        case 4 -> ls.deleteBook();
                    }
                    break;

                case 2:
                    System.out.println("\nCustomer Details:");
                    System.out.println("1. Creating the details of customer:");
                    System.out.println("2. Read the customer details:");
                    System.out.println("3. Update the customer details:");
                    System.out.println("4. Delete the customer details:");
                    int customerChoice = sc.nextInt();
                    sc.nextLine();
                    switch (customerChoice) {
                        case 1 -> ls.createCustomer();
                        case 2 -> ls.readCustomers();
                        case 3 -> ls.updateCustomer();
                        case 4 -> ls.deleteCustomer();
                    }
                    break;

                case 3:
                    System.out.println("\nIssuenReturn Details:");
                    System.out.println("1. Creating the issue and return details:");
                    System.out.println("2. Read the issue and return details:");
                    System.out.println("3. Update issue/return record");
                    System.out.println("4. Delete issue/return record");
                    int irChoice = sc.nextInt();
                    sc.nextLine();
                    switch (irChoice) {
                        case 1 -> ls.createIssuenReturn();
                        case 2 -> ls.readIssuenReturn();
                        case 3 -> ls.updateIssuenReturn();
                        case 4 -> ls.deleteIssuenReturn();
                    }
                    break;

                case 4:
                    sf.close();
                    System.out.println("Exiting system.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

}

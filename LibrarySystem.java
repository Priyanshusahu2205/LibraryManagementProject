package com.project;


import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class LibrarySystem {
	
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

        //fetching exist customer and book
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
            String customerInfo = "Customer not found";
            String bookInfo = "Book not found";

            if (ir.getCustomer() != null) {
                customerInfo = "Customer ID: " + ir.getCustomer().getCid();
            }

            if (ir.getBook() != null) {
                bookInfo = "Book ID: " + ir.getBook().getBid();
            }

            System.out.println("ID: " + ir.getId() + ", Loan Date: " + ir.getLoandate() +
                    ", Return Date: " + ir.getReturndate() + ", " + customerInfo + ", " + bookInfo);
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

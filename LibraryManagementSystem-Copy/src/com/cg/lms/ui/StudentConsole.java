package com.cg.lms.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.cg.lms.exception.LMSException;
import com.cg.lms.model.Book;
import com.cg.lms.model.RegisterEntry;
import com.cg.lms.model.Status;
import com.cg.lms.service.BookServiceImpl;
import com.cg.lms.service.IBookService;
import com.cg.lms.service.IRegisterEntryService;
import com.cg.lms.service.RegisterEntryServiceImpl;

public class StudentConsole {

	private DateTimeFormatter dtFormat;
	private Scanner scan;
	private IBookService bookService;
	private IRegisterEntryService regEntryService;
	private String currentUser;

	public StudentConsole(String currentUser) {
		this.currentUser = currentUser;
	}

	public void start() {
		scan = new Scanner(System.in);
		bookService = new BookServiceImpl();
		regEntryService = new RegisterEntryServiceImpl();
		dtFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		System.out.println("Welcome "+currentUser);

		int choice = -1;

		while (choice != 2) {

			System.out
					.println("[1]Reserve Book [2]LogOut");
			System.out.print("Choice> ");
			choice = scan.nextInt();
			
			switch(choice){
			case 1:reserveBook();break;
			}
		}
	}
	
	public void reserveBook() {
		try {
			List<Book> books = bookService.listBooks();
			if (books != null) {
				System.out.println("\tBcode\tStatus\tTitle");
				System.out.println("-----------------------------------------");
				for (Book book : books) {
					System.out
							.println(String.format("\t%s\t%s\t%s",
									book.getBcode(), book.getStatus(),
									book.getTitle()));
				}
				
				System.out.print("BCode> ");
				String bcode=scan.next();
				
				Book book = bookService.findBook(bcode);
				
				if(book==null){
					System.err.println("That Book Does Not Exist");
				}else if(book.getStatus()!=Status.AVAILABLE){
					System.err.println("That Book Already Reserved Or Issued");
				}else{
					RegisterEntry entry = new RegisterEntry();
					entry.setBcode(bcode);
					entry.setStudId(currentUser);
					entry.setReservedDate(LocalDate.now());
					
					if(regEntryService.reserveBook(entry)){
						System.out.println("Your Book Is Reserved");
					}else{
						System.err.println("Operation Failed!");
					}
				}
			} else {
				System.out.println("No Records Found!");
			}
		} catch (LMSException e) {
			System.err.println(e.getMessage());
		}
	}

}

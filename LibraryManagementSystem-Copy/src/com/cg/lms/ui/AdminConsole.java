package com.cg.lms.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.cg.lms.exception.LMSException;
import com.cg.lms.model.Book;
import com.cg.lms.model.RegisterEntry;
import com.cg.lms.model.User;
import com.cg.lms.service.BookServiceImpl;
import com.cg.lms.service.IBookService;
import com.cg.lms.service.IRegisterEntryService;
import com.cg.lms.service.RegisterEntryServiceImpl;

public class AdminConsole {

	private DateTimeFormatter dtFormat;
	private Scanner scan;
	private IBookService bookService;
	private IRegisterEntryService regEntryService;
	private String currentUser;

	public AdminConsole(String currentUser) {
		this.currentUser = currentUser;
	}

	public void start() {
		scan = new Scanner(System.in);
		bookService = new BookServiceImpl();
		regEntryService = new RegisterEntryServiceImpl();
		dtFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		System.out.println("Welcome " + currentUser);

		int choice = -1;

		while (choice != 5) {

			System.out
					.println("[1]View Books [2]Add Books [3]Issue Book [4]Return Book [5]LogOut");
			System.out.print("Choice> ");
			choice = scan.nextInt();

			switch (choice) {
			case 1:
				listBooks();
				break;
			case 2:
				addBook();
				break;
			case 3:
				issueBook();
				break;
			case 4:
				returnBook();
				break;
			}
		}

	}

	public void listBooks() {
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
			} else {
				System.out.println("No Records Found!");
			}
		} catch (LMSException e) {
			System.err.println(e.getMessage());
		}
	}

	public void addBook() {
		Book book = new Book();
		System.out.print("Book Code> ");
		book.setBcode(scan.next());
		System.out.print("Book Title> ");
		book.setTitle(scan.next());
		try {
			String bcode = bookService.saveBook(book);
			if (bcode == null)
				System.err.println("Operation Failed!");
			else
				System.out.println("Book Saved! with code " + bcode);
		} catch (LMSException e) {
			System.err.println(e.getMessage());
		}
	}

	public void issueBook() {
		try {
			List<RegisterEntry> entries = regEntryService.listReservedBooks();
			if (entries != null) {
				System.out.println("\tLogId\tStudId\tReservedOn\tBCode\tTitle");
				System.out
						.println("-----------------------------------------------------");
				for (RegisterEntry entry : entries) {
					System.out.println(String.format("\t%d\t%s\t%s\t%s\t%s",
							entry.getLogId(), entry.getStudId(), entry
									.getReservedDate().format(dtFormat), entry
									.getBcode(), entry.getTitle()));
				}

				System.out.print("LogId> ");
				int logid = scan.nextInt();
				RegisterEntry regEntry = regEntryService.findEntry(logid);
				if (regEntry != null && regEntry.getIssueDate() == null) {
					regEntry.setIssueDate(LocalDate.now());

					if (regEntryService.issueBook(regEntry))
						System.out.println("Reserved Book Issued");
					else
						System.err.println("Operation Failed!");
				} else {
					System.err.println("Record Not Found UnIssued");
				}
			} else {
				System.out.println("No Books Reserved");
			}
		} catch (LMSException e) {
			System.err.println(e.getMessage());
		}
	}

	public void returnBook() {
		try {
			List<RegisterEntry> entries = regEntryService.listIssuedBooks();
			if (entries != null) {
				System.out.println("\tLogId\tStudId\tIssuedOn\tBCode\tTitle");
				System.out
						.println("-----------------------------------------------------");
				for (RegisterEntry entry : entries) {
					System.out.println(String.format("\t%d\t%s\t%s\t%s\t%s",
							entry.getLogId(), entry.getStudId(), entry
									.getIssueDate().format(dtFormat), entry
									.getBcode(), entry.getTitle()));
				}

				System.out.print("LogId> ");
				int logid = scan.nextInt();
				RegisterEntry regEntry = regEntryService.findEntry(logid);
				if (regEntry != null && regEntry.getReturnDate() == null) {
					regEntry.setReturnDate(LocalDate.now());

					if (regEntryService.returnBook(regEntry))
						System.out.println("Issued Book REturned");
					else
						System.err.println("Operation Failed!");
				} else {
					System.err.println("Record Not Found UnReturned");
				}
			} else {
				System.out.println("No Records Found");
			}
		} catch (LMSException e) {
			System.err.println(e.getMessage());
		}
	}
}

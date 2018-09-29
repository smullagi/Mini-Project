package com.cg.lms.service;

import java.util.List;

import com.cg.lms.exception.LMSException;
import com.cg.lms.model.Book;

public interface IBookService {

	String saveBook(Book book) throws LMSException;
	List<Book> listBooks() throws LMSException;
	Book findBook(String bcode) throws LMSException;
}

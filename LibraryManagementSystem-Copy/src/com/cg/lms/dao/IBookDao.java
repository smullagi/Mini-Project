package com.cg.lms.dao;

import java.util.List;

import com.cg.lms.exception.LMSException;
import com.cg.lms.model.Book;

public interface IBookDao {

	String saveBook(Book book) throws LMSException;
	List<Book> listBooks() throws LMSException;
	Book findBook(String bcode) throws LMSException;
}

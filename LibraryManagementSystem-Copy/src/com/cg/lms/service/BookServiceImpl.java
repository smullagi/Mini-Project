package com.cg.lms.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.lms.dao.BookDaoImpl;
import com.cg.lms.dao.IBookDao;
import com.cg.lms.exception.LMSException;
import com.cg.lms.model.Book;
import com.cg.lms.model.Status;

public class BookServiceImpl implements IBookService {

	private IBookDao bookDao;
	
	public BookServiceImpl() {
		bookDao=new BookDaoImpl();
	}
	
	public boolean isValidBCode(String bcode) throws LMSException{
		Pattern bcodePattern = Pattern.compile("B[0-9]{3,}");
		Matcher bcodeMatcher = bcodePattern.matcher(bcode);
		
		boolean isValid=bcodeMatcher.matches();
		if(!isValid)
			throw new LMSException("Bcode must start with 'B' followed by atleast three digits");
		return isValid;
	}
	
	public boolean isValidTitle(String title) throws LMSException{
		Pattern titlePattern = Pattern.compile("[A-Za-z0-9\\s]{3,}");
		Matcher titleMatcher = titlePattern.matcher(title);
		
		boolean isValid=titleMatcher.matches();
		if(!isValid)
			throw new LMSException("Title must be atleast three chars in length");
		return isValid;
	}
	
	public boolean isValidBook(Book book) throws LMSException{
		return isValidBCode(book.getBcode()) && isValidTitle(book.getTitle());
	}
	
	@Override
	public String saveBook(Book book) throws LMSException {
		String bcode=null;
		if(book!=null && isValidBook(book)){
			book.setStatus(Status.AVAILABLE);
			bcode=bookDao.saveBook(book);
		}
		return bcode;
	}

	@Override
	public List<Book> listBooks() throws LMSException {
		return bookDao.listBooks();
	}

	@Override
	public Book findBook(String bcode) throws LMSException {
		return bookDao.findBook(bcode);
	}

}

package com.cg.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.lms.exception.LMSException;
import com.cg.lms.model.Book;
import com.cg.lms.model.Status;
import com.cg.lms.util.ConnectionProvider;

public class BookDaoImpl implements IBookDao {

	private Logger log = Logger.getLogger("BookDAO");

	@Override
	public String saveBook(Book book) throws LMSException {
		String bcode = null;
		if (book != null) {
			try (Connection con = ConnectionProvider.DEFAULT_INSTANCE.getConnection();
					PreparedStatement st = con
							.prepareStatement(IQueryMapper.ADD_BOOK);) {
				st.setString(1, book.getBcode());
				st.setString(2, book.getTitle());
				st.setString(3, book.getStatus().toString());
				int count = st.executeUpdate();

				if (count > 0)
					bcode = book.getBcode();

			} catch (SQLException e) {
				log.error(e);
				throw new LMSException("Unable To Save Book");
			}
		}
		return bcode;
	}

	@Override
	public List<Book> listBooks() throws LMSException {
		List<Book> booksList = null;
		try (Connection con = ConnectionProvider.DEFAULT_INSTANCE.getConnection();
				PreparedStatement st = con
						.prepareStatement(IQueryMapper.LIST_BOOKS);) {
			
			ResultSet rs = st.executeQuery();
			
			booksList=new ArrayList<Book>();			
			while(rs.next()){
				Book book = new Book();
				book.setBcode(rs.getString("bcode"));
				book.setTitle(rs.getString("title"));
				book.setStatus(Status.valueOf(rs.getString("status")));
				booksList.add(book);
			}
			
			if(booksList.size()==0)
				booksList=null;
		} catch (SQLException e) {
			log.error(e);
			throw new LMSException("Unable To Fetch Books");
		}
		return booksList;
	}

	@Override
	public Book findBook(String bcode) throws LMSException {
		Book book = null;
		try (Connection con = ConnectionProvider.DEFAULT_INSTANCE.getConnection();
				PreparedStatement st = con
						.prepareStatement(IQueryMapper.FIND_BOOK);) {
			st.setString(1, bcode);
			ResultSet rs = st.executeQuery();
						
			if(rs.next()){
				book = new Book();
				book.setBcode(rs.getString("bcode"));
				book.setTitle(rs.getString("title"));
				book.setStatus(Status.valueOf(rs.getString("status")));				
			}
			
		} catch (SQLException e) {
			log.error(e);
			throw new LMSException("Unable To Fetch Book");
		}
		return book;
	}
}

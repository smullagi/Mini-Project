package com.cg.lms.dao;

import java.time.LocalDate;
import java.util.List;

import com.cg.lms.exception.LMSException;
import com.cg.lms.model.RegisterEntry;

public interface IRegisterEntryDao {
	List<RegisterEntry> listReservedBooks() throws LMSException;

	List<RegisterEntry> listIssuedBooks() throws LMSException;
	RegisterEntry findEntry(int logid) throws LMSException;
	
	boolean reserveBook(RegisterEntry regEntry) throws LMSException;
	boolean issueBook(RegisterEntry regEntry) throws LMSException;
	boolean returnBook(RegisterEntry regEntry) throws LMSException;	
}

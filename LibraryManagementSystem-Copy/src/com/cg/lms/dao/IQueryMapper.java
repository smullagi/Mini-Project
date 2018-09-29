package com.cg.lms.dao;

public interface IQueryMapper {

	public static final String GET_USER="SELECT * FROM lmsUsers WHERE unm=?";
	
	public static final String ADD_BOOK="INSERT INTO lmsBooks VALUES(?,?,?)";
	public static final String LIST_BOOKS="SELECT * FROM lmsBooks";
	public static final String FIND_BOOK="SELECT * FROM lmsBooks WHERE bcode=?";

	public static final String LIST_RESERVED_BOOKS="SELECT logid,lmsBooks.bcode,studid,title,resvdt FROM lmsRegister INNER JOIN lmsBooks ON lmsRegister.bcode=lmsBooks.bcode WHERE isudt IS NULL";
	public static final String LIST_ISSUED_BOOKS="SELECT logid,lmsBooks.bcode,studid,title,isudt FROM lmsRegister INNER JOIN lmsBooks ON lmsRegister.bcode=lmsBooks.bcode WHERE isudt IS NOT NULL AND rtndt IS NULL";
	public static final String FIND_ENTRY="SELECT * FROM lmsRegister WHERE logid=?";
	
	public static final String UPDATE_BOOK_STATUS="UPDATE lmsBooks SET status=? WHERE bcode=?";
	public static final String LOG_RESERVE_BOOK="INSERT INTO lmsRegister(logid,bcode,studid,resvdt) VALUES(logid_seq.nextval,?,?,?)";
	public static final String LOG_ISSUE_BOOK="UPDATE lmsRegister SET isudt=? WHERE logid=?";
	public static final String LOG_RETURN_BOOK="UPDATE lmsRegister SET rtndt=? WHERE logid=?";	
}

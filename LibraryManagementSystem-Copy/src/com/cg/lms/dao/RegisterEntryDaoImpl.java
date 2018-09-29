package com.cg.lms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.lms.exception.LMSException;
import com.cg.lms.model.RegisterEntry;
import com.cg.lms.model.Status;
import com.cg.lms.util.ConnectionProvider;

public class RegisterEntryDaoImpl implements IRegisterEntryDao {

	private Logger log = Logger.getLogger("RegisterEntryDAO");

	@Override
	public List<RegisterEntry> listReservedBooks() throws LMSException {
		List<RegisterEntry> entries = null;
		try (Connection con = ConnectionProvider.DEFAULT_INSTANCE.getConnection();
				PreparedStatement st = con
						.prepareStatement(IQueryMapper.LIST_RESERVED_BOOKS);) {

			ResultSet rs = st.executeQuery();

			entries = new ArrayList<RegisterEntry>();
			while (rs.next()) {
				RegisterEntry entry = new RegisterEntry();
				entry.setLogId(rs.getInt("logid"));
				entry.setBcode(rs.getString("bcode"));
				entry.setStudId(rs.getString("studid"));
				entry.setTitle(rs.getString("title"));
				entry.setReservedDate(rs.getDate("resvdt").toLocalDate());
				entries.add(entry);
			}

			if (entries.size() == 0)
				entries = null;
		} catch (SQLException e) {
			log.error(e);
			throw new LMSException("Unable To Fetech Records");
		}
		return entries;
	}

	@Override
	public List<RegisterEntry> listIssuedBooks() throws LMSException {
		List<RegisterEntry> entries = null;
		try (Connection con = ConnectionProvider.DEFAULT_INSTANCE.getConnection();
				PreparedStatement st = con
						.prepareStatement(IQueryMapper.LIST_ISSUED_BOOKS);) {

			ResultSet rs = st.executeQuery();

			entries = new ArrayList<RegisterEntry>();
			while (rs.next()) {
				RegisterEntry entry = new RegisterEntry();
				entry.setLogId(rs.getInt("logid"));
				entry.setBcode(rs.getString("bcode"));
				entry.setStudId(rs.getString("studid"));
				entry.setTitle(rs.getString("title"));
				entry.setIssueDate(rs.getDate("isudt").toLocalDate());
				entries.add(entry);
			}

			if (entries.size() == 0)
				entries = null;
		} catch (SQLException e) {
			log.error(e);
			throw new LMSException("Unable To Fetech Records");
		}
		return entries;
	}

	@Override
	public boolean reserveBook(RegisterEntry regEntry) throws LMSException {
		boolean isDone = false;
		if (regEntry != null) {

			try (Connection con = ConnectionProvider.DEFAULT_INSTANCE.getConnection();) {
				try (PreparedStatement st = con
						.prepareStatement(IQueryMapper.LOG_RESERVE_BOOK);
						PreparedStatement st2 = con
								.prepareStatement(IQueryMapper.UPDATE_BOOK_STATUS);) {

					con.setAutoCommit(false);
					st.setString(1, regEntry.getBcode());
					st.setString(2, regEntry.getStudId());
					st.setDate(3, Date.valueOf(regEntry.getReservedDate()));

					int count = st.executeUpdate();

					if (count > 0) {
						st2.setString(1, Status.RESERVED.toString());
						st2.setString(2, regEntry.getBcode());

						count = st2.executeUpdate();

						if (count > 0) {
							con.commit();
							isDone = true;
						}
					}
				} catch (SQLException e) {
					con.rollback();
					log.error(e);
					throw new LMSException("Unable To Complete Operation");
				}
			} catch (SQLException e) {
				log.error(e);
				throw new LMSException("Unable To Complete Operation");
			}
		}
		return isDone;
	}

	@Override
	public boolean issueBook(RegisterEntry regEntry) throws LMSException {
		boolean isDone = false;

		try (Connection con = ConnectionProvider.DEFAULT_INSTANCE.getConnection();) {
			try (PreparedStatement st = con
					.prepareStatement(IQueryMapper.LOG_ISSUE_BOOK);
					PreparedStatement st2 = con
							.prepareStatement(IQueryMapper.UPDATE_BOOK_STATUS);) {

				con.setAutoCommit(false);
				st.setDate(1, Date.valueOf(regEntry.getIssueDate()));
				st.setInt(2, regEntry.getLogId());
				
				int count = st.executeUpdate();

				if (count > 0) {
					st2.setString(1, Status.ISSUED.toString());
					st2.setString(2, regEntry.getBcode());

					count = st2.executeUpdate();

					if (count > 0) {
						con.commit();
						isDone = true;
					}
				}
			} catch (SQLException e) {
				con.rollback();
				log.error(e);
				throw new LMSException("Unable To Complete Operation");
			}
		} catch (SQLException e) {
			log.error(e);
			throw new LMSException("Unable To Complete Operation");
		}

		return isDone;
	}

	@Override
	public boolean returnBook(RegisterEntry regEntry) throws LMSException {
		boolean isDone = false;

		try (Connection con = ConnectionProvider.DEFAULT_INSTANCE.getConnection();) {
			try (PreparedStatement st = con
					.prepareStatement(IQueryMapper.LOG_RETURN_BOOK);
					PreparedStatement st2 = con
							.prepareStatement(IQueryMapper.UPDATE_BOOK_STATUS);) {

				con.setAutoCommit(false);
				st.setDate(1, Date.valueOf(regEntry.getReturnDate()));
				st.setInt(2, regEntry.getLogId());
				
				int count = st.executeUpdate();

				if (count > 0) {
					st2.setString(1, Status.AVAILABLE.toString());
					st2.setString(2, regEntry.getBcode());

					count = st2.executeUpdate();

					if (count > 0) {
						con.commit();
						isDone = true;
					}
				}
			} catch (SQLException e) {
				con.rollback();
				log.error(e);
				
				throw new LMSException("Unable To Complete Operation");
			}
		} catch (SQLException e) {
			log.error(e);
			throw new LMSException("Unable To Complete Operation");
		}

		return isDone;
	}

	@Override
	public RegisterEntry findEntry(int logid) throws LMSException {
		RegisterEntry entry = null;
		try (Connection con = ConnectionProvider.DEFAULT_INSTANCE.getConnection();
				PreparedStatement st = con
						.prepareStatement(IQueryMapper.FIND_ENTRY);) {

			st.setInt(1, logid);
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				entry = new RegisterEntry();
				entry.setLogId(rs.getInt("logid"));
				entry.setBcode(rs.getString("bcode"));
				entry.setStudId(rs.getString("studid"));
				Date resvdt=rs.getDate("resvdt");
				entry.setReservedDate(resvdt==null?null:resvdt.toLocalDate());				
				Date isudt=rs.getDate("isudt");
				entry.setIssueDate(isudt==null?null:isudt.toLocalDate());				
				Date rtndt=rs.getDate("rtndt");
				entry.setReturnDate(rtndt==null?null:rtndt.toLocalDate());				
}
		} catch (SQLException e) {
			log.error(e);
			throw new LMSException("Unable To Fetech Records");
		}
		return entry;
	}
}

package org.sitenv.directvendortools.web.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.sitenv.directvendortools.web.dto.AccountRegisterTO;
import org.sitenv.directvendortools.web.util.ApplicationQueries;
import org.sitenv.directvendortools.web.util.ApplicationUtil;
import org.sitenv.directvendortools.web.util.HashException;
import org.sitenv.directvendortools.web.util.SaltedPasswordHashUtil;

public class AccountRegisterDAO extends BaseDAO {
	
	public static int registerAccount(final AccountRegisterTO accountRegisterTO) throws SQLException,NamingException,PropertyVetoException,HashException{
		
		final Connection connection = getDbConnection();
		final PreparedStatement ps= connection.prepareStatement(ApplicationQueries.ACCOUNT_REGISTER_QUERY);
		ps.setString(1, accountRegisterTO.getCompanyName());
		ps.setString(2, accountRegisterTO.getFirstName());
		ps.setString(3, accountRegisterTO.getLastName());
		ps.setString(4, accountRegisterTO.getEmail().toUpperCase());
		ps.setString(5, SaltedPasswordHashUtil.getSecurePassword(accountRegisterTO.getPassword(), 32));
		ps.setTimestamp(6, ApplicationUtil.getSqlTimeStamp());
		ps.setTimestamp(7, ApplicationUtil.getSqlTimeStamp());
		
		final int  applicationStatus = ps.executeUpdate();
		ApplicationUtil.close(ps);
		ApplicationUtil.close(connection);
		return applicationStatus;
	}
	
	public static boolean isEmailAvailable(String emailAddress) throws SQLException,NamingException,PropertyVetoException{
		
		final Connection connection = getDbConnection();
		final PreparedStatement ps= connection.prepareStatement(ApplicationQueries.EMAIL_CHECK_QUERY);
		ps.setString(1, emailAddress.toUpperCase());
		final ResultSet rs = ps.executeQuery();
		boolean isEmailAvailable = true;
		while(rs.next())
		{
		   isEmailAvailable =  rs.getInt(1) == 0 ? true : false;	
		}
		ApplicationUtil.closeAll(rs);
		return isEmailAvailable;
	}

}

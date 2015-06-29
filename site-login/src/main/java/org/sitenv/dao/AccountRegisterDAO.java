package org.sitenv.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sitenv.dto.AccountRegisterTO;
import org.sitenv.util.ApplicationQueries;
import org.sitenv.util.ApplicationUtil;
import org.sitenv.util.ConnectionPool;

public class AccountRegisterDAO {
	
	public static int registerAccount(final AccountRegisterTO accountRegisterTO) throws SQLException,PropertyVetoException{
		
		final Connection connection = ConnectionPool.getInstance().getConnection();
		final PreparedStatement ps= connection.prepareStatement(ApplicationQueries.ACCOUNT_REGISTER_QUERY);
		ps.setString(1, accountRegisterTO.getCompanyName());
		ps.setString(2, accountRegisterTO.getCompanyPOC());
		ps.setString(3, accountRegisterTO.getFirstName());
		ps.setString(4, accountRegisterTO.getLastName());
		ps.setString(5, accountRegisterTO.getEmail());
		ps.setString(6, accountRegisterTO.getPassword());
		ps.setTimestamp(7, ApplicationUtil.getSqlTimeStamp());
		ps.setTimestamp(8, ApplicationUtil.getSqlTimeStamp());
		
		final int  applicationStatus = ps.executeUpdate();
		ApplicationUtil.close(ps);
		ApplicationUtil.close(connection);
		return applicationStatus;
	}
	
	public static boolean isEmailAvailable(String emailAddress) throws SQLException,PropertyVetoException{
		
		final Connection connection = ConnectionPool.getInstance().getConnection();
		final PreparedStatement ps= connection.prepareStatement(ApplicationQueries.EMAIL_CHECK_QUERY);
		ps.setString(1, emailAddress);
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

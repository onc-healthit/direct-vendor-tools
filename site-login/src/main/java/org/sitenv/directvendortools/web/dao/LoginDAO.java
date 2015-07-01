package org.sitenv.directvendortools.web.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.sitenv.directvendortools.web.dto.UserLoginTO;
import org.sitenv.directvendortools.web.util.ApplicationQueries;
import org.sitenv.directvendortools.web.util.ApplicationUtil;
import org.sitenv.directvendortools.web.util.ConnectionPool;

public class LoginDAO {
	
public static boolean loginAuthentication(UserLoginTO userLoginTO) throws SQLException,PropertyVetoException{
		
		final Connection connection = ConnectionPool.getInstance().getConnection();
		final PreparedStatement ps= connection.prepareStatement(ApplicationQueries.LOGIN_AUTH_QUERY);
		ps.setString(1, userLoginTO.getUserName().toUpperCase());
		final ResultSet rs = ps.executeQuery();
		boolean userAuthentication = false;
		while(rs.next())
		{
			userAuthentication =  rs.getString(2).equals(userLoginTO.getPassword()) ? true : false;	
		}
		ApplicationUtil.closeAll(rs);
		return userAuthentication;
	}

}

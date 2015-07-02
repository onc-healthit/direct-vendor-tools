package org.sitenv.directvendortools.web.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.sitenv.directvendortools.web.dto.UserLoginTO;
import org.sitenv.directvendortools.web.util.ApplicationQueries;
import org.sitenv.directvendortools.web.util.ApplicationUtil;
import org.sitenv.directvendortools.web.util.HashException;
import org.sitenv.directvendortools.web.util.SaltedPasswordHashUtil;

public class LoginDAO extends BaseDAO {
	
	public static boolean loginAuthentication(UserLoginTO userLoginTO) throws SQLException,NamingException,PropertyVetoException, HashException{
		
		final Connection connection = getDbConnection();
		final PreparedStatement ps= connection.prepareStatement(ApplicationQueries.LOGIN_AUTH_QUERY);
		ps.setString(1, userLoginTO.getUserName().toUpperCase());
		final ResultSet rs = ps.executeQuery();
		boolean userAuthentication = false;
		while(rs.next())
		{
			userAuthentication =  SaltedPasswordHashUtil.validatePassword(userLoginTO.getPassword(), rs.getString(2));	
		}
		ApplicationUtil.closeAll(rs);
		return userAuthentication;
	}

}

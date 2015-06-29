package org.sitenv.bl;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import org.sitenv.dao.LoginDAO;
import org.sitenv.dto.UserLoginTO;


public class LoginProcess {
	
	public static String loginAuthentication(UserLoginTO userLoginTO) throws SQLException,PropertyVetoException
	{
		
		return String.valueOf(LoginDAO.loginAuthentication(userLoginTO));
	}


}

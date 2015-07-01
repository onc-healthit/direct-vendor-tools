package org.sitenv.directvendortools.web.bl;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import org.sitenv.directvendortools.web.dao.LoginDAO;
import org.sitenv.directvendortools.web.dto.UserLoginTO;


public class LoginProcess {
	
	public static String loginAuthentication(UserLoginTO userLoginTO) throws SQLException,PropertyVetoException
	{
		
		return String.valueOf(LoginDAO.loginAuthentication(userLoginTO));
	}


}

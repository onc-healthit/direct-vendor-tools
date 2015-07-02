package org.sitenv.directvendortools.web.bl;


import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.sitenv.directvendortools.web.dao.AccountRegisterDAO;
import org.sitenv.directvendortools.web.dto.AccountRegisterTO;
import org.sitenv.directvendortools.web.util.HashException;

public class AccountRegisterProcess {
	
	public static int registerAccount(final AccountRegisterTO accountRegisterTO)throws SQLException,NamingException,PropertyVetoException, HashException
	{
		return AccountRegisterDAO.registerAccount(accountRegisterTO);
	}

	
	public static boolean isEmailAvailable(final String emailAddress)throws SQLException,NamingException,PropertyVetoException
	{
		return AccountRegisterDAO.isEmailAvailable(emailAddress);
	}

}

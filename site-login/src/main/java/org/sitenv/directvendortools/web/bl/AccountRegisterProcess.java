package org.sitenv.directvendortools.web.bl;


import java.beans.PropertyVetoException;
import java.sql.SQLException;

import org.sitenv.directvendortools.web.dao.AccountRegisterDAO;
import org.sitenv.directvendortools.web.dto.AccountRegisterTO;
import org.sitenv.directvendortools.web.util.HashException;

public class AccountRegisterProcess {
	
	public static int registerAccount(final AccountRegisterTO accountRegisterTO)throws SQLException,PropertyVetoException, HashException
	{
		return AccountRegisterDAO.registerAccount(accountRegisterTO);
	}

	
	public static boolean isEmailAvailable(final String emailAddress)throws SQLException,PropertyVetoException
	{
		return AccountRegisterDAO.isEmailAvailable(emailAddress);
	}

}

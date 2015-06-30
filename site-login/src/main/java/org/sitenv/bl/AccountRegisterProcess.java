package org.sitenv.bl;


import java.beans.PropertyVetoException;
import java.sql.SQLException;

import org.sitenv.dao.AccountRegisterDAO;
import org.sitenv.dto.AccountRegisterTO;

public class AccountRegisterProcess {
	
	public static int registerAccount(final AccountRegisterTO accountRegisterTO)throws SQLException,PropertyVetoException
	{
		return AccountRegisterDAO.registerAccount(accountRegisterTO);
	}

	
	public static boolean isEmailAvailable(final String emailAddress)throws SQLException,PropertyVetoException
	{
		return AccountRegisterDAO.isEmailAvailable(emailAddress);
	}

}

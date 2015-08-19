package org.sitenv.directvendortools.web.bl;


import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringEscapeUtils;
import org.sitenv.directvendortools.web.dao.AccountRegisterDAO;
import org.sitenv.directvendortools.web.dto.AccountRegisterTO;
import org.sitenv.directvendortools.web.util.HashException;

public class AccountRegisterProcess {
	
	public static int registerAccount(final AccountRegisterTO accountRegisterTO)throws SQLException,NamingException,PropertyVetoException, HashException
	{
		htmlEncoding(accountRegisterTO);
		return AccountRegisterDAO.registerAccount(accountRegisterTO);
	}

	
	public static boolean isEmailAvailable(final String emailAddress)throws SQLException,NamingException,PropertyVetoException
	{
		return AccountRegisterDAO.isEmailAvailable(emailAddress);
	}
	
	private static void htmlEncoding(final AccountRegisterTO accountRegisterTO)
	{
		accountRegisterTO.setCompanyName(StringEscapeUtils.escapeHtml4(accountRegisterTO.getCompanyName()));
		accountRegisterTO.setCompanyPOC(StringEscapeUtils.escapeHtml4(accountRegisterTO.getCompanyPOC()));
		accountRegisterTO.setFirstName(StringEscapeUtils.escapeHtml4(accountRegisterTO.getFirstName()));
		accountRegisterTO.setLastName(StringEscapeUtils.escapeHtml4(accountRegisterTO.getLastName()));
		accountRegisterTO.setEmail(StringEscapeUtils.escapeHtml4(accountRegisterTO.getEmail()));
	}

}

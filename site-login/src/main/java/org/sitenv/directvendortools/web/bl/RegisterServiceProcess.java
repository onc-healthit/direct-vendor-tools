package org.sitenv.directvendortools.web.bl;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringEscapeUtils;
import org.sitenv.directvendortools.web.dao.RegisterServiceDAO;
import org.sitenv.directvendortools.web.dto.DirectSystemTO;
import org.sitenv.directvendortools.web.dto.ResultSetTO;


public class RegisterServiceProcess {
	
	public static int registerService(final DirectSystemTO directSystemTO)throws SQLException,NamingException,PropertyVetoException
	{
		htmlEncoding(directSystemTO);
		return RegisterServiceDAO.registerService(directSystemTO);
	}
	
	public static boolean isDirectSysEmailAvailable(final String directEmailAddress)throws SQLException,NamingException,PropertyVetoException
	{
		return RegisterServiceDAO.isDirectSysEmailAvailable(directEmailAddress);
	}

	
	public static ResultSetTO readAllDirectSystems(String userEmail)throws SQLException,NamingException,PropertyVetoException
	{
		final ResultSetTO resultSet = new ResultSetTO();
		resultSet.getResults().addAll(RegisterServiceDAO.readAllDirectSystems(userEmail));
		resultSet.initializeCounts();
		return resultSet;
	}
	
	public static boolean isUpdatedDirectSysEmailAvailable(final String directEmailAddress, final int directSysId)throws SQLException,NamingException,PropertyVetoException
	{
		return RegisterServiceDAO.checkUpdatedDirectSysEmail(directEmailAddress, directSysId);
	}
	
	public static int updateDirectorySystem(final DirectSystemTO directSystemTO)throws SQLException,NamingException,PropertyVetoException
	{
		htmlEncoding(directSystemTO);
		return RegisterServiceDAO.updateRegisterService(directSystemTO);
	}
	
	private static void htmlEncoding(final DirectSystemTO directSystemTO)
	{
		directSystemTO.setCehrtLabel(StringEscapeUtils.escapeHtml4(directSystemTO.getCehrtLabel()));
		directSystemTO.setOrganizationName(StringEscapeUtils.escapeHtml4(directSystemTO.getOrganizationName()));
		directSystemTO.setDirectEmailAddress(StringEscapeUtils.escapeHtml4(directSystemTO.getDirectEmailAddress()));
		directSystemTO.setPointOfContact(StringEscapeUtils.escapeHtml4(directSystemTO.getPointOfContact()));
		directSystemTO.setPocFirstName(StringEscapeUtils.escapeHtml4(directSystemTO.getPocFirstName()));
		directSystemTO.setPocLastName(StringEscapeUtils.escapeHtml4(directSystemTO.getPocLastName()));
		directSystemTO.setTimezone(StringEscapeUtils.escapeHtml4(directSystemTO.getTimezone()));
		directSystemTO.setDirectTrustMembership(StringEscapeUtils.escapeHtml4(directSystemTO.getDirectTrustMembership()));
		directSystemTO.setUserEmailAddress(StringEscapeUtils.escapeHtml4(directSystemTO.getUserEmailAddress()));
		directSystemTO.setNotes(StringEscapeUtils.escapeHtml4(directSystemTO.getNotes()));
	}


}

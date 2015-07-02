package org.sitenv.directvendortools.web.bl;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.sitenv.directvendortools.web.dao.RegisterServiceDAO;
import org.sitenv.directvendortools.web.dto.DirectSystemTO;
import org.sitenv.directvendortools.web.dto.ResultSetTO;


public class RegisterServiceProcess {
	
	public static int registerService(final DirectSystemTO directSystemTO)throws SQLException,NamingException,PropertyVetoException
	{
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
		return RegisterServiceDAO.updateRegisterService(directSystemTO);
	}


}

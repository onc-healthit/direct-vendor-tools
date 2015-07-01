package org.sitenv.directvendortools.web.bl;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import org.sitenv.directvendortools.web.dao.RegisterServiceDAO;
import org.sitenv.directvendortools.web.dto.DirectSystemTO;
import org.sitenv.directvendortools.web.dto.ResultSetTO;


public class RegisterServiceProcess {
	
	public static int registerService(final DirectSystemTO directSystemTO)throws SQLException,PropertyVetoException
	{
		return RegisterServiceDAO.registerService(directSystemTO);
	}
	
	public static boolean isDirectSysEmailAvailable(final String directEmailAddress)throws SQLException,PropertyVetoException
	{
		return RegisterServiceDAO.isDirectSysEmailAvailable(directEmailAddress);
	}

	
	public static ResultSetTO readAllDirectSystems(String userEmail)throws SQLException,PropertyVetoException
	{
		final ResultSetTO resultSet = new ResultSetTO();
		resultSet.getResults().addAll(RegisterServiceDAO.readAllDirectSystems(userEmail));
		resultSet.initializeCounts();
		return resultSet;
	}


}

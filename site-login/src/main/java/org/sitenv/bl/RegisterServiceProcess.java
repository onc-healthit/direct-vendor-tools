package org.sitenv.bl;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import org.sitenv.dao.RegisterServiceDAO;
import org.sitenv.dto.DirectSystemTO;
import org.sitenv.dto.ResultSetTO;


public class RegisterServiceProcess {
	
	public static int registerService(final DirectSystemTO directSystemTO)throws SQLException,PropertyVetoException
	{
		return RegisterServiceDAO.registerService(directSystemTO);
	}
	
	public static ResultSetTO readAllDirectSystems()throws SQLException,PropertyVetoException
	{
		final ResultSetTO resultSet = new ResultSetTO();
		resultSet.getResults().addAll(RegisterServiceDAO.readAllDirectSystems());
		resultSet.initializeCounts();
		return resultSet;
	}


}

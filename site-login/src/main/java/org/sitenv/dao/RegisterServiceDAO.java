package org.sitenv.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sitenv.dto.DirectSystemTO;
import org.sitenv.util.ApplicationConstants;
import org.sitenv.util.ApplicationQueries;
import org.sitenv.util.ApplicationUtil;
import org.sitenv.util.ConnectionPool;

public class RegisterServiceDAO {

public static int registerService(final DirectSystemTO directSystemTO) throws SQLException,PropertyVetoException{
		
		final Connection connection = ConnectionPool.getInstance().getConnection();
		final PreparedStatement ps= connection.prepareStatement(ApplicationQueries.REGISTER_SERVICE_QUERY);
		ps.setString(1, directSystemTO.getCehrtLabel());
		ps.setString(2, directSystemTO.getOrganizationName());
		ps.setString(3, directSystemTO.getDirectEmailAddress());
		ps.setString(4, directSystemTO.getPointOfContact());
		ps.setString(5, directSystemTO.getPocFirstName());
		ps.setString(6, directSystemTO.getPocLastName());
		ps.setString(7, directSystemTO.getTimezone());
		ps.setString(8, directSystemTO.getDirectTrustMembership());
		ps.setDate(9, ApplicationUtil.convertStringToDate(directSystemTO.getAvailFromDate(),ApplicationConstants.DATE_FORMAT));
		ps.setDate(10, ApplicationUtil.convertStringToDate(directSystemTO.getAvailToDate(),ApplicationConstants.DATE_FORMAT));
		
		final int  registerServiceStatus = ps.executeUpdate();
		ApplicationUtil.close(ps);
		ApplicationUtil.close(connection);
		return registerServiceStatus;
	}

public static List<DirectSystemTO> readAllDirectSystems() throws SQLException,PropertyVetoException{
	
	final Connection connection = ConnectionPool.getInstance().getConnection();
	final PreparedStatement ps= connection.prepareStatement(ApplicationQueries.READ_DIRECT_SYSTEMS);
	final List<DirectSystemTO> directSystemToList = new ArrayList<>() ;
	final ResultSet rs = ps.executeQuery();
	DirectSystemTO directSystemTo;
	while(rs.next())
	{
		directSystemTo = new DirectSystemTO();
		directSystemTo.setCehrtLabel(rs.getString(ApplicationConstants.CEHRT_LABEL));
		directSystemTo.setOrganizationName(rs.getString(ApplicationConstants.ORG_NAME));
		directSystemTo.setDirectEmailAddress(rs.getString(ApplicationConstants.DIRECT_EMAIL_ADDRESS));
		directSystemTo.setPointOfContact(rs.getString(ApplicationConstants.POINT_OF_CONTACT));
		directSystemTo.setPocFirstName(rs.getString(ApplicationConstants.POC_FIRST_NAME));
		directSystemTo.setPocLastName(rs.getString(ApplicationConstants.POC_LAST_NAME));
		directSystemTo.setDirectTrustMembership(rs.getString(ApplicationConstants.DIRECT_TRUST_MEM));
		directSystemTo.setAvailFromDate(rs.getString(ApplicationConstants.AVAIL_FROM_DATE));
		directSystemTo.setAvailToDate(rs.getString(ApplicationConstants.AVAIL_TO_DATE));
		directSystemToList.add(directSystemTo);
		
	}
	return directSystemToList;
}
}

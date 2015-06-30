package org.sitenv.util;


public class ApplicationQueries {
	
	public static String ACCOUNT_REGISTER_QUERY = "Insert into SITEUSER (company , company_poc, firstname, lastname,emailaddress,"
												+ "password,createtimestamp,lastupdttimestamp)" +
	                                              "values (?,?,?,?,?,?,?,?)";
	
	public static String EMAIL_CHECK_QUERY= "Select count(*) from SITEUSER where emailaddress = ?";
	
	public static String LOGIN_AUTH_QUERY= "Select emailaddress,password from SITEUSER where emailaddress = ?";
	
	
	public static String REGISTER_SERVICE_QUERY = "Insert into REGISTER_SERVICE (cehrtLabel , organizationName, directEmailAddress, pointOfContact,pocFirstName,"
			+ "pocLastName,timezone,directTrustMembership,availFromDate,availToDate)" +
            	"values (?,?,?,?,?,?,?,?,?,?)";
	
	public static String READ_DIRECT_SYSTEMS = "select * from REGISTER_SERVICE";
	
}

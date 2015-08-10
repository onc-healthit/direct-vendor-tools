package org.sitenv.directvendortools.web.util;


public class ApplicationQueries {
	
	public static String ACCOUNT_REGISTER_QUERY = "Insert into SITEUSER (company , firstname, lastname,emailaddress,"
												+ "password,createtimestamp,lastupdttimestamp)" +
	                                              "values (?,?,?,?,?,?,?)";
	
	public static String EMAIL_CHECK_QUERY= "Select count(*) from SITEUSER where emailaddress = ?";
	
	public static String LOGIN_AUTH_QUERY= "Select emailaddress,password from SITEUSER where emailaddress = ?";
	
	
	public static String REGISTER_SERVICE_QUERY = "Insert into REGISTER_SERVICE (cehrtLabel , organizationName, directEmailAddress, pointOfContact,pocFirstName,"
			+ "pocLastName,timezone,directTrustMembership,availFromDate,availToDate,useremailaddress,notes)" +
            	"values (?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static String UPDATE_REGISTER_SERVICE_QUERY = "Update REGISTER_SERVICE  set cehrtLabel = ? , organizationName = ?, "
			+ "directEmailAddress = ?, pointOfContact = ?,pocFirstName = ?,"
			+ "pocLastName = ?,timezone = ?,directTrustMembership = ?,availFromDate = ?,availToDate =?,useremailaddress = ?,notes=?" 
            + "where ID = ?";
	
	public static String DIRECT_SYS_EMAIL_CHECK_QUERY= "Select count(*) from register_service where directemailaddress = ?";
	
	public static String UPD_DIRECT_SYS_EMAIL_CHECK_QUERY= "Select count(*) from register_service where directemailaddress = ? and Id != ?";
	
	public static String READ_DIRECT_SYSTEMS = "select * from REGISTER_SERVICE";
	
}

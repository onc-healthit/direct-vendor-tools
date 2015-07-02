package org.sitenv.directvendortools.web.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class BaseDAO {

	protected static Connection getDbConnection () throws NamingException, SQLException
	{
		
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/site_login");
		Connection conn = ds.getConnection();
		
		
		return conn;

	}
}

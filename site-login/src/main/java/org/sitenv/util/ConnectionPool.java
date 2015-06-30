/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sitenv.util;

import java.beans.PropertyVetoException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 *
 * @author muddanarajesh
 */
public class ConnectionPool {
    
    private static ComboPooledDataSource dataSource;
    private static String driverClass = "org.postgresql.Driver";
    private static String dbURL = "jdbc:postgresql://localhost:5432/site_login";
    private static String userName = "siteuser";
    private static String password = "siteuser";
    private static int minPoolSize = 5;
    private static int maxPoolSize = 20;
    private static int initialPoolSize = 5;
    private static int acquireIncrement = 5;
    
    
    
    public static synchronized ComboPooledDataSource getInstance() throws PropertyVetoException{
        
      if(dataSource == null){  
        dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass( driverClass ); //loads the jdbc driver            
        dataSource.setJdbcUrl( dbURL);
        dataSource.setUser(userName);                                  
        dataSource.setPassword(password);                                  
	
        // the settings below are optional -- c3p0 can work with defaults
        dataSource.setMinPoolSize(minPoolSize);                                     
        dataSource.setAcquireIncrement(acquireIncrement);
        dataSource.setMaxPoolSize(maxPoolSize); 
        dataSource.setInitialPoolSize(initialPoolSize);
    }
    return dataSource;
  }
    
    public static synchronized void closePool(){
        if(dataSource !=null){
            dataSource.close();
        }
    }
}

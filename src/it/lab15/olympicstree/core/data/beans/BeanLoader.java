package it.lab15.olympicstree.core.data.beans;

import it.lab15.olympicstree.commons.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanLoader {
	
	private static final Logger LOG = LoggerFactory.getLogger(BeanLoader.class);
	
	public static final String JDBC_DRIVER_HSQLDB = "org.hsqldb.jdbc.JDBCDriver";
	public static final String JDBC_URL = "jdbc:hsqldb:file:data/hsqldb_data/OlympicsOpenData.db";
			
	public static Connection createDbConnection() throws SQLException, ClassNotFoundException{
		return DbUtils.createConnection(JDBC_URL, JDBC_DRIVER_HSQLDB);
	}
	

}

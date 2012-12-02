package it.lab15.olympicstree.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbUtils {

	public static void finalize(ResultSet rst, Statement stm, Connection cnn){
		finalize(rst);
		finalize(stm);
		finalize(cnn);
	}

	public static void finalize(ResultSet rst, Statement stm){
		finalize(rst);
		finalize(stm);
	}

	public static void finalize(ResultSet rst){
		try {
			rst.close();
		} catch (Exception ex){}
	}

	public static void finalize(Statement stm){
		try {
			stm.close();
		} catch (Exception ex){}
	}
	
	public static void finalize(Connection cnn){
		try {
			cnn.close();
		} catch (Exception ex){}
	}

	public static Connection createConnection(String url, String driver) throws SQLException, ClassNotFoundException {
		loadDriver(driver);
		return DriverManager.getConnection(url);
	}
	
	public static void loadDriver(String driverName) throws ClassNotFoundException {
		Class.forName(driverName);
	}

}

/*
 * Copyright 1997-2012 SoftSolutions! srl 
 * All Rights Reserved. 
 * 
 * NOTICE: All information contained herein is, and remains the property of SoftSolutions! srl 
 * The intellectual and technical concepts contained herein are proprietary to SoftSolutions! srl and 
 * may be covered by EU, U.S. and other Foreign Patents, patents in process, and 
 * are protected by trade secret or copyright law. 
 * Dissemination of this information or reproduction of this material is strictly forbidden 
 * unless prior written permission is obtained from SoftSolutions! srl.
 * Any additional licenses, terms and conditions, if any, are defined in file 'LICENSE.txt', 
 * which may be part of this software package.
 */

package it.lab15.olympicstree.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Purpose: this class is mainly for ...
 * 
 * Project Name : CSVReader First created by: marco.bonati Creation date:
 * 30/nov/2012
 * 
 **/
public class DbImporterEditions {

	public static final String CSV_DATA_FOLDER = "data";
	public static final String CSV_DATA_FILE = CSV_DATA_FOLDER + "/vwMedalsByCountry.csv";
	
	public static final String DB_TABLE_NAME = "EDITIONS_COMPLETE";
	//public static final String DB_TABLE_NAME = "NEWTABLE";
	
	private static SimpleDateFormat DATE_PARSER = new SimpleDateFormat("yyyy-MM-DD");

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Connection dbConnection = null;
		Connection csvConnection = null;
		Statement cvsStmt = null;
		ResultSet csvResults = null;

		try {
			// load the drivers into memory
			loadDriver("org.relique.jdbc.csv.CsvDriver");
			loadDriver("org.hsqldb.jdbc.JDBCDriver");

			// create a connection. The first command line parameter is assumed
			// to
			// be the directory in which the .csv files are held
			csvConnection = DriverManager.getConnection("jdbc:relique:csv:" + CSV_DATA_FOLDER);
			// create a Statement object to execute the query with
			cvsStmt = csvConnection.createStatement();
			// Select the ID and NAME columns from sample.csv
			csvResults = cvsStmt.executeQuery("SELECT * FROM vwMedalsByCountry");
			System.out.println("CSV opened");

			System.out.println("Opening database...");
			dbConnection = DriverManager.getConnection("jdbc:hsqldb:file:/Users/marcus/develop/java/OlympicsTree/data/hsqldb_data/OlympicsOpenData.db");
			System.out.println("Database opened " + dbConnection.getMetaData().getURL());
			
			System.out.println("Deleting old data...");
			Statement clearStatement = dbConnection.createStatement();
			clearStatement.execute("DELETE FROM "+DB_TABLE_NAME);
			clearStatement.close();
			System.out.println("Old data deleted.");
			
			PreparedStatement dbStatement = null;

			String sqlInsert = "INSERT INTO "+DB_TABLE_NAME+" (" + getColumnNames() + ") VALUES (" + getColumnPlaceholders() + ")";
			System.out.println("Insert statement: " + sqlInsert);

			dbStatement = dbConnection.prepareStatement(sqlInsert);

			int importedRows = 0;
			int fileRowIndex = 2;
			
			// dump out the results
			System.out.println("Starting import...");
			while (csvResults.next()) {
				// System.out.println("OLYMedalId= " +
				// csvResults.getString("OLYMedalId") +
				// "   Name= " + csvResults.getString("Name") +
				// " Date of Birth = " + csvResults.getString("DateOfBirth")
				// );
				System.out.println("Importing row: " + fileRowIndex);
				dbStatement = fillDbInsertStatement(csvResults, dbStatement);
				dbStatement.execute();
				dbStatement.clearParameters();
				importedRows++;
				fileRowIndex++;
				System.out.println("Imported " + importedRows + " rows...");
			}
			System.out.println("Import done!");

			// clean up
			csvResults.close();
			cvsStmt.close();
			csvConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbConnection.close();
				csvConnection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	private static String getColumnPlaceholders() {
		StringBuffer sb = new StringBuffer();
		sb.append(" ?, ");
		sb.append(" ?, ");
		sb.append(" ?, ");
		sb.append(" ?, ");
		sb.append(" ?, ");
		sb.append(" ?, ");
		sb.append(" ?, ");
		sb.append(" ?, ");
		sb.append(" ?, ");
		sb.append(" ? ");
		return sb.toString();
	}

	private static String getColumnNames() {
		StringBuffer sb = new StringBuffer();
		sb.append(" GAMES_ID, ");
		sb.append(" YEAR, ");
		sb.append(" HOST_CITY, ");
		sb.append(" COUNTRY_CODE, ");
		sb.append(" COUNTRY_NAME, ");
		sb.append(" RANK, ");
		sb.append(" GOLD, ");
		sb.append(" SILVER, ");
		sb.append(" BRONZE, ");
		sb.append(" TOTAL ");
		return sb.toString();
	}

	private static PreparedStatement fillDbInsertStatement(ResultSet csvResults, PreparedStatement dbStatement) throws SQLException, ParseException {
		int paramIndex = 1;
		//"gamesId","year","hostCity","countryCode","countryName","rank","gold","silver","bronze","total"
		dbStatement.setInt(paramIndex++, csvResults.getInt("gamesId"));
		dbStatement.setInt(paramIndex++, csvResults.getInt("year"));
		dbStatement.setString(paramIndex++, csvResults.getString("hostCity"));
		dbStatement.setString(paramIndex++, csvResults.getString("countryCode"));
		dbStatement.setString(paramIndex++, csvResults.getString("countryName"));
		dbStatement.setInt(paramIndex++, csvResults.getInt("rank"));
		dbStatement.setInt(paramIndex++, csvResults.getInt("gold"));
		dbStatement.setInt(paramIndex++, csvResults.getInt("silver"));
		dbStatement.setInt(paramIndex++, csvResults.getInt("bronze"));
		dbStatement.setInt(paramIndex++, csvResults.getInt("total"));

		return dbStatement;
	}

	private static boolean toBoolean(String string) {
		if (string == null) {
			return false;
		} else if (string.equalsIgnoreCase("Y")) {
			return true;
		} else {
			return false;
		}
	}

	public static java.sql.Date toSQLDate(String date) throws ParseException {
		try {
			if (date==null){
				return null;
			} 
			Date dateObj = toDate(date);
			if (dateObj==null){
				return null;
			}
			return new java.sql.Date(dateObj.getTime());
		} catch (Exception ex){
			System.out.println("Unparseable date: '"+date+"'");
			return null;
		}
	}

	public static Date toDate(String date) throws ParseException {
		if (date==null){
			return null;
		}
		if (date.trim().length()==0){
			return null;
		}
		return DATE_PARSER.parse(date);
	}

	private static void loadDriver(String driverName) throws ClassNotFoundException {
		Class.forName(driverName);
	}

}

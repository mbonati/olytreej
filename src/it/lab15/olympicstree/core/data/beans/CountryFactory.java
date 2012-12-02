package it.lab15.olympicstree.core.data.beans;

import it.lab15.olympicstree.commons.Chrono;
import it.lab15.olympicstree.commons.DbUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountryFactory implements BeanFactory<Country> {

	private static final Logger LOG = LoggerFactory.getLogger(CountryFactory.class);

	/**
	 * Load all country items
	 */
	@Override
	public List<Country> loadAll() throws Exception {
		LOG.debug("loadAll called...");
		return load(null);
	}

	/**
	 * Load filtered country items on COUNTRIES_COMPLETE view
	 */
	@Override
	public List<Country> load(String queryString) throws Exception {
		LOG.debug("load called (queryString={})...", queryString);
		String sqlQuery = "SELECT * FROM COUNTRIES_COMPLETE";
		if (queryString!=null){
			sqlQuery += " WHERE " + queryString;
		}
		return loadCustom(sqlQuery);
	}
	
	/**
	 * Load all countries by custom sql clause
	 */
	@Override
	public List<Country> loadCustom(String queryString) throws Exception {
		LOG.debug("loadCustom called (queryString={})...", queryString);
		Connection cnn = null;
		try {
			cnn = BeanLoader.createDbConnection();
			List<Country> countries = loadCountries(cnn, queryString);
			LOG.debug("load done successfully. Returned {} items",
					countries.size());
			return countries;
		} catch (Exception ex) {
			LOG.error("load error: {}", ex.getMessage(), ex);
			return null;
		} finally {
			DbUtils.finalize(cnn);
		}
	}

	/**
	 * Load countries with specific connection and sql query
	 * @param cnn
	 * @param sqlQuery
	 * @return
	 * @throws Exception
	 */
	private static List<Country> loadCountries(Connection cnn, String sqlQuery) throws Exception {
		LOG.debug("loadCountries called (sqlQuery={})...", sqlQuery);
		Chrono chrono = new Chrono();
		Statement stm = null;
		ResultSet rst = null;
		List<Country> dataList = new ArrayList<Country>();
		try {
			stm = cnn.createStatement();
			rst = stm.executeQuery(sqlQuery);
			while(rst.next()){
				Country country = new Country(rst.getString("MEDALCOUNTRYCODE"), rst.getString("NAME_EN"));
				dataList.add(country);
			}
			LOG.debug("loadCountries done: {} items return", dataList.size());
			return dataList;
		} catch (Exception ex){
			LOG.error("loadCountries error: {}", ex.getMessage(), ex);
			throw ex;
		} finally {
			chrono.stop();
			DbUtils.finalize(rst, stm);
		}
	}
	
	public static void main(String[] args){
		LOG.debug("CountryFactory test starting...");
		Chrono chrono = new Chrono();
		Connection cnn = null;
		try {
			List<Country> countries = new CountryFactory().loadAll();
			LOG.info("Total countries retrieved: {}", countries.size());
		} catch (Exception ex){
			ex.printStackTrace();
		} finally {
			DbUtils.finalize(cnn);
		}
		chrono.stop();
	}

	
}

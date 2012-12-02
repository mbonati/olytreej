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

public class EditionFactory implements BeanFactory<Edition> {

	private static final Logger LOG = LoggerFactory.getLogger(EditionFactory.class);

	@Override
	public List<Edition> loadAll() throws Exception {
		LOG.debug("loadAll called...");
		return load(null);
	}

	@Override
	public List<Edition> load(String queryString) throws Exception {
		LOG.debug("load called (queryString={})...", queryString);
		String sqlQuery = "SELECT * FROM EDITIONS ";
		if (queryString!=null){
			sqlQuery += " WHERE " + queryString;
		}
		return loadCustom(sqlQuery);
	}

	@Override
	public List<Edition> loadCustom(String queryString) throws Exception {
		LOG.debug("loadCustom called (queryString={})...", queryString);
		Connection cnn = null;
		try {
			cnn = BeanLoader.createDbConnection();
			List<Edition> editions = loadEditions(cnn, queryString);
			LOG.debug("load done successfully. Returned {} items",
					editions.size());
			return editions;
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
	private static List<Edition> loadEditions(Connection cnn, String sqlQuery) throws Exception {
		LOG.debug("loadEditions called (sqlQuery={})...", sqlQuery);
		Chrono chrono = new Chrono();
		Statement stm = null;
		ResultSet rst = null;
		List<Edition> dataList = new ArrayList<Edition>();
		try {
			stm = cnn.createStatement();
			rst = stm.executeQuery(sqlQuery);
			while(rst.next()){
				Edition edition = new Edition(rst.getInt("GAMES_ID"), rst.getInt("YEAR"), rst.getString("HOST_CITY"));
				dataList.add(edition);
			}
			LOG.debug("loadEditions done: {} items return", dataList.size());
			return dataList;
		} catch (Exception ex){
			LOG.error("loadEditions error: {}", ex.getMessage(), ex);
			throw ex;
		} finally {
			chrono.stop();
			DbUtils.finalize(rst, stm);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOG.debug("EditionFactory test starting...");
		Chrono chrono = new Chrono();
		Connection cnn = null;
		try {
			List<Edition> countries = new EditionFactory().loadAll();
			LOG.info("Total editions retrieved: {}", countries.size());
		} catch (Exception ex){
			ex.printStackTrace();
		} finally {
			DbUtils.finalize(cnn);
		}
		chrono.stop();
	}
}

package it.lab15.olympicstree.core.data.beans;

import java.util.List;

public interface BeanFactory<T extends Bean> {

	public List<T> loadAll() throws Exception;

	public List<T> load(String queryString) throws Exception;

	public List<T> loadCustom(String queryString) throws Exception;

}

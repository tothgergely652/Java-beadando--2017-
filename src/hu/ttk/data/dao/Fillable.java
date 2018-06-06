package hu.ttk.data.dao;

import java.util.HashMap;

public interface Fillable {
	/**
	 * feltötli az entitást
	 * @param keyValuePair
	 */
	public void fill(HashMap keyValuePair);
}

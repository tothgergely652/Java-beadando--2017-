package hu.ttk.data.dao.studies;

import java.util.ArrayList;

import hu.ttk.data.entity.Studies;

public interface StudiesDAOProvider {
	public ArrayList getAllStudy() throws Exception;
	public void addStudy(Studies study) throws Exception;
	public void deleteStudy(Studies study) throws Exception;
	public void editStudy(Studies study) throws Exception;
}

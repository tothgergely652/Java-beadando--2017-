package hu.ttk.data.dao.cv;

import java.util.ArrayList;

import hu.ttk.data.entity.CV;

public interface CVDAOProvider {
	public ArrayList getAllCV() throws Exception;
	public void addCV(CV cv) throws Exception;
	public void deleteCV(CV cv) throws Exception;
	public void editCV(CV cv) throws Exception;
	public void deleteStudies(String where) throws Exception;
	public void deleteJob(String where) throws Exception;
}
